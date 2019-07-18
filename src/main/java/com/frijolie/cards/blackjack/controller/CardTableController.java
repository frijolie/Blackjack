package com.frijolie.cards.blackjack.controller;

import com.frijolie.cards.blackjack.Command;
import com.frijolie.cards.blackjack.CommandInvoker;
import com.frijolie.cards.blackjack.io.GameIO;
import com.frijolie.cards.blackjack.model.cards.BlackjackHand;
import com.frijolie.cards.blackjack.model.cards.Card;
import com.frijolie.cards.blackjack.model.cards.Hand;
import com.frijolie.cards.blackjack.model.game.BlackjackGame;
import com.frijolie.cards.blackjack.model.game.Game;
import com.frijolie.cards.blackjack.model.players.BlackjackPlayer;

import java.util.Optional;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

class CardTableController {

  @FXML
  private HBox dealerHandHBox;

  @FXML
  private HBox playerHandHBox;

  @FXML
  private Button standButton;

  @FXML
  private Button doubleButton;

  @FXML
  private Button splitButton;

  @FXML
  private Button hitButton;

  @FXML
  private Button surrenderButton;

  @FXML
  private Label handResultLabel;

  private Game game;

  private SuperController superController;

  private ObservableList<Card> playerCards;

  private ObservableList<Card> dealerCards;

  private BlackjackPlayer player;

  private Hand playerHand;

  private Hand dealerHand;

  private CommandInvoker command;

  private Command hit;

  private Command doubleDown;

  private Command insurance;

  private Command split;

  private Command stand;

  private Command surrender;

  private Text playerScore;

  private Text dealerScore;

  private AnchorPane anchorPane;

  /**
   * No-arg constructor. Assembles an FXML file into a usable JavaFX component.
   */
  CardTableController(SuperController superController) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CardTable.fxml"));
    this.superController = superController;
    loader.setController(this);

    try {
      anchorPane = loader.load();
    } catch (Exception e) {
      System.err.println("CardTable.fxml could not be loaded");
      e.printStackTrace();
    }

    game = superController.getGame();
    player = (BlackjackPlayer) game.getPlayer();
    dealerHand = game.getDealer().getHand();
    playerCards = game.getPlayer().getHand().getCards();
    playerHand = player.getHand();
    command = new CommandInvoker();
    playerScore = new Text();
    playerScore.getStyleClass().add("score");
    dealerScore = new Text();
    dealerScore.getStyleClass().add("score");
    dealerCards = dealerHand.getCards();

    configureBindings();
    configureListeners();
    configureCommands();
    configureActions();
    displayInitialHand();
  }

  /**
   * Displays the initial two cards that are dealt to the player and dealer. The Dealers first card
   * is dealt face down and not visible to the player.
   */
  private void displayInitialHand() {
    paintDealerHandFaceDown();
    paintPlayerHand();
  }

  /**
   * This clears the nodes from the DealerHBox and adds them again. Currently this is necessary to
   * ensure that the Text node (dealerScore) is displayed at the end of the row. Had some trouble
   * with setViewOrder()
   */
  private void paintDealerHandFaceUp() {
    dealerHandHBox.getChildren().clear();
    for (Card card : dealerCards) {
      dealerHandHBox.getChildren().add(getCardFace(card));
    }
    dealerHandHBox.getChildren().add(dealerScore);
  }

  /**
   * Clears the nodes in the dealer hand and displays one card face down and the rest face up.
   */
  private void paintDealerHandFaceDown() {
    dealerHandHBox.getChildren().clear();
    dealerHandHBox.getChildren().addAll(getCardBack(), getCardFace(dealerCards.get(1)));
  }

  /**
   * This clears the nodes from the PlayerHBox and draws them again. Currently this is necessary to
   * ensure that the Text node (playerScore) is displayed at the end of the row. Had some trouble
   * with setViewOrder()
   */
  private void paintPlayerHand() {
    playerHandHBox.getChildren().clear();
    playerCards.forEach(card -> playerHandHBox.getChildren().add(getCardFace(card)));
    playerHandHBox.getChildren().add(playerScore);
  }

  /**
   * Returns the ImageView used to display as the card back, namely for face down cards.
   *
   * @return the Image used for the card backs.
   */
  private ImageView getCardBack() {
    // TODO make the card backs a configuration option in the GUI
    String[] color = {"blue", "red"};
    String design = "123";
    return GameIO.getImageView(
        "/images/backs/" + color[0] + design.charAt(2) + ".png", 75, 109, true, true);
  }

  /**
   * Returns an ImageView with the proper card face used to display on the TableScene.
   *
   * @param card that you wish to see the face
   * @return an ImageView containing the card image
   */
  private ImageView getCardFace(Card card) {
    return GameIO.getImageView("/images/cards/" + card.toString() + ".png", 75, 109, true, true);
  }

  private void configureActions() {
    hitButton.setOnAction(e -> handleHitButton());
    standButton.setOnAction(e -> handleStandButton());
    doubleButton.setOnAction(e -> handleDoubleDownButton());
    surrenderButton.setOnAction(e -> handleSurrenderButton());
  }

  /**
   * Sets up all of the necessary bindings from the controller to properties in the model class(es).
   */
  private void configureBindings() {
    BlackjackHand playerHand = (BlackjackHand) this.playerHand;
    BlackjackHand dealerHand = (BlackjackHand) this.dealerHand;
    BlackjackGame game = (BlackjackGame) this.game;
    // hides the split button unless the player hand can be split
    splitButton.visibleProperty().bind(playerHand.canSplitProperty());
    splitButton.managedProperty().bind(splitButton.visibleProperty());

    // hides the double button unless the player hand can double down
    doubleButton.visibleProperty().bind(playerHand.canDoubleProperty());
    doubleButton.managedProperty().bind(doubleButton.visibleProperty());

    // disabled the hit button if the player hand cannot HIT
    hitButton.disableProperty().bind(playerHand.canHitProperty().not());

    surrenderButton.visibleProperty().bind(game.offerSurrenderProperty());
    surrenderButton.managedProperty().bind(surrenderButton.visibleProperty());

    dealerScore.textProperty().bind(dealerHand.scoreProperty().asString());
    playerScore.textProperty().bind(playerHand.scoreProperty().asString());
  }

  @FXML
  private void handleHitButton() {
    setStatusText("Player hits");
    command.execute(hit);
  }

  @FXML
  private void handleStandButton() {
    setStatusText("Player stands");
    command.execute(stand);
    // need to flip over dealer face-down card
    paintDealerHandFaceUp();
  }

  @FXML
  private void handleDoubleDownButton() {
    setStatusText("Player doubles down");
    command.execute(doubleDown);
    // need to flip over dealer face-down card
    paintDealerHandFaceUp();
  }

  @FXML
  private void handleSplitButton() {
    setStatusText("Player splits");
    command.execute(split);
  }

  @FXML
  private void handleSurrenderButton() {
    setStatusText("Player surrenders ... coward");
    command.execute(surrender);
  }

  /**
   * Sets up all of the proper listeners that handle change events and update the GUI with the
   * changes.
   */
  private void configureListeners() {
    configurePlayerListeners();
    configureDealerListeners();
    configureGameListeners();
  }

  /**
   * Sets the status text of the Scene.
   *
   * @param msg to be displayed on the GUI
   */
  private void setStatusText(String msg) {
    superController.setStatusText(msg);
  }

  /**
   * Fires the respective methods when a button is clicked.
   */
  private void configureCommands() {
    hit = player::hit;
    doubleDown = player::doubleDown;
    insurance = player::buyInsurance;
    split = player::split;
    stand = player::stand;
    surrender = player::surrender;
  }

  private void configurePlayerListeners() {
    BlackjackHand playerHand = (BlackjackHand) this.playerHand;
    player
        .isActiveProperty()
        .addListener(
            (observable, oldValue, isActive) -> {
              if (!isActive) {
                standButton.setDisable(true);
                splitButton.setDisable(true);
                doubleButton.setDisable(true);
                surrenderButton.setDisable(true);
              }
            });

    playerHand
        .isBustProperty()
        .addListener(
            (observable, oldValue, isBust) -> {
              if (isBust) {
                setStatusText("Player BUSTs. Game Over");
                // need to flip over dealer face-down card
                paintDealerHandFaceUp();
              }
            });

    playerCards.addListener(
        (ListChangeListener<? super Card>) c -> {
            while (c.next()) {
              if (c.wasAdded() || c.wasRemoved()) {
                paintPlayerHand();
              }
            }
        });
  }

  private void configureDealerListeners() {
    dealerCards.addListener(
        (ListChangeListener<? super Card>) c -> {
            while (c.next()) {
              if (c.wasAdded()) {
                paintDealerHandFaceUp();
              } else if (c.wasRemoved()) {
                displayInitialHand();
                dealerScore.setText("");
              }
            }
        });
  }

  private void configureGameListeners() {
    BlackjackGame game = (BlackjackGame) this.game;
    game.gameIsOverProperty()
        .addListener(
            (observable, oldValue, gameOver) -> {
              if (gameOver) {
                handResultLabel.setText("You " + playerHand.getHandResult().toString());
              }
            });
    game.offerInsuranceProperty()
        .addListener(
            ((observable, oldValue, newValue) -> {
              if (newValue) {
                setStatusText("Need Insurance?");
                showInsuranceAlert();
              }
            }));
  }

  public void showInsuranceAlert() {
    setStatusText("Need Insurance?");
    Alert alert =
        new Alert(
            Alert.AlertType.CONFIRMATION,
            "Would you like to purchase insurance?",
            ButtonType.YES,
            ButtonType.NO);
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.YES) {
      player.buyInsurance();
    }
  }

  public final AnchorPane getTableScene() {
    return anchorPane;
  }
}
