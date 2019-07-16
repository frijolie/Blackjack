package com.frijolie.cards.blackjack;

import com.frijolie.cards.blackjack.controller.MainWindowController;
import com.frijolie.cards.blackjack.controller.MenuBarController;
import com.frijolie.cards.blackjack.controller.StatusBarController;
import com.frijolie.cards.blackjack.controller.TableController;
import com.frijolie.cards.blackjack.model.game.BlackjackGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BlackjackMain extends Application {

  private BorderPane mainWindow;
  private MenuBar menuBar;
  private StackPane statusBar;
  private AnchorPane table;
  private MainWindowController mainWindowController;
  private MenuBarController menuBarController;
  private StatusBarController statusBarController;
  private TableController tableController;
  private BlackjackGame game;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    game = new BlackjackGame();
    assemble();
    primaryStage.setScene(new Scene(mainWindow));
    primaryStage.setAlwaysOnTop(true);
    primaryStage.setResizable(false);
    primaryStage.setTitle("Blackjack v1.0");
    primaryStage.show();
    primaryStage.toFront();
  }

  private void assemble() {
    mainWindow = getMainWindow();
    menuBar = getMenuBar();
    statusBar = getStatusBar();
    table = getTableScene();
    mainWindow.setTop(menuBar);
    mainWindow.setBottom(statusBar);
    mainWindow.setCenter(table);
  }

  private BorderPane getMainWindow() {
    mainWindowController = new MainWindowController();
    return mainWindowController.getMainWindow();
  }

  private MenuBar getMenuBar() {
    menuBarController = new MenuBarController();
    return menuBarController.getMenuBar();
  }

  private StackPane getStatusBar() {
    statusBarController = new StatusBarController();
    return statusBarController.getStatusBar();
  }

  private AnchorPane getTableScene() {
    tableController = new TableController();
    tableController.injectModel(game, statusBarController);
    return tableController.getTableScene();
  }
}
