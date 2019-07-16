module com.frijolie.cards.blackjack {
  requires javafx.controls;
  requires javafx.fxml;

  exports com.frijolie.cards.blackjack;

  opens com.frijolie.cards.blackjack.controller to javafx.fxml;
  opens com.frijolie.cards.blackjack.model.cards;
  opens com.frijolie.cards.blackjack.model.game;
  opens com.frijolie.cards.blackjack.model.players;
}
