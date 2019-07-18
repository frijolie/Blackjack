package com.frijolie.cards.blackjack;

import com.frijolie.cards.blackjack.controller.SuperController;
import com.frijolie.cards.blackjack.model.game.BlackjackGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BlackjackMain extends Application {

  private SuperController superController;
  private BlackjackGame game;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    game = new BlackjackGame();
    primaryStage.setScene(getScene());
    primaryStage.setAlwaysOnTop(true);
    primaryStage.setResizable(false);
    primaryStage.setTitle("Blackjack v1.0");
    primaryStage.show();
    primaryStage.toFront();
  }

  private Scene getScene() {
    superController = new SuperController(game);
    return superController.getScene();
  }
}
