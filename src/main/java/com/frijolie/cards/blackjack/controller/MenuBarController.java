package com.frijolie.cards.blackjack.controller;

import com.frijolie.cards.blackjack.io.GameIO;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

public class MenuBarController {

  @FXML
  private MenuItem newGameMenuItem;

  @FXML
  private MenuItem quitMenuItem;

  @FXML
  private MenuItem preferencesMenuItem;

  @FXML
  private MenuItem rulesMenuItem;

  @FXML
  private MenuItem aboutMenuItem;

  @FXML
  private MenuItem hintMenuItem;

  private MenuBar menuBar;
  private SuperController superController;

  /**
   * No-arg constructor. Assembles an FXML file into a usable JavaFX component.
   */
  MenuBarController(SuperController superController) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
    loader.setController(this);
    this.superController = superController;
    try {
      menuBar = loader.load();
    } catch (Exception e) {
      System.err.println("MainMenu.fxml could not be loaded");
      e.printStackTrace();
    }
  }

  /**
   * Configures things after the FXML has been loaded.
   */
  @FXML
  public void initialize() {
    setMenuItemIcons();
    setMenuEventHandlers();
    setAccelerators();
  }

  /**
   * Returns a MenuBar to be used in the GUI.
   *
   * @return a MenuBar component
   */
  final MenuBar getMenuBar() {
    return menuBar;
  }

  private void setMenuEventHandlers() {
    quitMenuItem.setOnAction(e -> Platform.exit());
    hintMenuItem.setOnAction(e -> displayHintDialog());
  }

  /**
   * Configures the accelerators for menus and menu items.
   */
  private void setAccelerators() {
    aboutMenuItem.setAccelerator(KeyCombination.keyCombination("F1"));
    rulesMenuItem.setAccelerator(KeyCombination.keyCombination("F2"));
  }

  /**
   * Adds a graphic next to each menuItem.
   */
  private void setMenuItemIcons() {
    ImageView quitMenuItemImage = new ImageView(GameIO.getImage("/images/menu/exit.png"));
    quitMenuItemImage.setFitHeight(25);
    quitMenuItemImage.setFitWidth(25);
    quitMenuItem.setGraphic(quitMenuItemImage);

    ImageView newMenuItemImage = new ImageView(GameIO.getImage("/images/menu/new.png"));
    newMenuItemImage.setFitWidth(25);
    newMenuItemImage.setFitHeight(25);
    newGameMenuItem.setGraphic(newMenuItemImage);

    ImageView preferencesItemImage = new ImageView(GameIO.getImage("/images/menu/preferences.png"));
    preferencesItemImage.setFitHeight(25);
    preferencesItemImage.setFitWidth(25);
    preferencesMenuItem.setGraphic(preferencesItemImage);

    ImageView rulesItemImage = new ImageView(GameIO.getImage("/images/menu/rules.png"));
    rulesItemImage.setFitWidth(25);
    rulesItemImage.setFitHeight(25);
    rulesMenuItem.setGraphic(rulesItemImage);

    ImageView aboutItemImage = new ImageView(GameIO.getImage("/images/menu/about.png"));
    aboutItemImage.setFitHeight(25);
    aboutItemImage.setFitWidth(25);
    aboutMenuItem.setGraphic(aboutItemImage);

    ImageView hintItemImage = new ImageView(GameIO.getImage("/images/menu/hint.png"));
    hintItemImage.setFitHeight(25);
    hintItemImage.setFitWidth(25);
    hintMenuItem.setGraphic(hintItemImage);
  }

  private void displayHintDialog() {
    superController.displayHint();
  }
}
