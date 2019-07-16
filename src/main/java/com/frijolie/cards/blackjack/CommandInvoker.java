package com.frijolie.cards.blackjack;

import java.util.Objects;

public class CommandInvoker {

  /**
   * Executes the command on the receiver.
   *
   * @param command to be executed
   */
  public void execute(Command command) {
    String message = "You must pass a non-null command to be executed";
    Objects.requireNonNull(command, message);
    command.execute();
  }
}
