package com.frijolie.cards.blackjack.controller;

import com.frijolie.cards.blackjack.model.game.BlackjackGame;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class SuperController {

  private MainWindowController mainWindowController;
  private MenuBarController menuBarController;
  private StatusBarController statusBarController;
  private CardTableController cardTableController;
  private BorderPane mainWindow;
  private MenuBar menuBar;
  private StackPane statusBar;
  private AnchorPane cardTable;
  private BlackjackGame game;

  /**
   * Constructor.
   * @param game a reference to the game.
   */
  public SuperController(BlackjackGame game) {
    this.game = game;
    mainWindow = getMainWindow();
    mainWindow.setTop(getMenuBar());
    mainWindow.setCenter(getCardTable());
    mainWindow.setBottom(getStatusBar());
  }

  /**
   * Returns a BorderPane used as the main window. All components are placed in a region within the
   * mainWindow.
   * @return a BorderPane.
   */
  private BorderPane getMainWindow() {
    mainWindowController = new MainWindowController(this);
    return mainWindowController.getMainWindow();
  }

  /**
   * Returns a MenuBar which houses the menuItems necessary for the user interface.
   * @return a MenuBar
   */
  private MenuBar getMenuBar() {
    menuBarController = new MenuBarController(this);
    return menuBarController.getMenuBar();
  }

  /**
   * Returns a StackPane which is used as a status field in the user interface.
   * @return a StackPane
   */
  private StackPane getStatusBar() {
    statusBarController = new StatusBarController(this);
    return statusBarController.getStatusBar();
  }

  /**
   * Returns an AnchorPane which is used as the card table in the user interface.
   * @return an AnchorPane
   */
  private AnchorPane getCardTable() {
    cardTableController = new CardTableController(this);
    return cardTableController.getTableScene();
  }

  /**
   * Returns a Scene that is placed on the Stage in the application.
   * @return a Scene
   */
  public Scene getScene() {
    return new Scene(mainWindow);
  }

  /**
   * Returns a reference to the game model.
   * @return a reference to the BlackjackGame
   */
  public final BlackjackGame getGame() {
    return game;
  }

  /**
   * Returns a reference to the MainWindow controller.
   * @return a reference to the MainWindow
   */
  public final MainWindowController getMainWindowController() {
    return mainWindowController;
  }

  /**
   * Returns a reference to the MenuBar controller.
   * @return a reference to the MenuBar controller
   */
  public final MenuBarController getMenuBarController() {
    return menuBarController;
  }

  /**
   * Returns a reference to the StatusBar controller.
   * @return a reference to the StatusBar controller
   */
  public final StatusBarController getStatusBarController() {
    return statusBarController;
  }

  /**
   * Returns a reference to the CardTable controller.
   * @return a reference to the CardTable controller
   */
  public final CardTableController getCardTableController() {
    return cardTableController;
  }

  /**
   * Passes a string to the proper controller to be displayed in the statusbar.
   * @param message to be displayed
   */
  final void setStatusText(String message) {
    statusBarController.setStatusText(message);
  }

  /**
   * Makes a call to display the hint in the user interface.
   */
  final void displayHint() {
    mainWindowController.displayHint();
  }
}
