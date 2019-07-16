package com.frijolie.cards.blackjack.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HintDialogController {
  @FXML Label hintLabel;

  void setText(String message) {
    hintLabel.setText(message);
  }
}
