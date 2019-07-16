package com.frijolie.cards.blackjack.io;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameIO {

  private static InputStream readUrl(String url) {
    InputStream input = null;
    return input = GameIO.class.getResourceAsStream(url);
  }

  public static Image getImage(String url) {
    return new Image(readUrl(url));
  }

  private static Image getImage(String url, double width, double height,
                               boolean preserveRatio, boolean smooth) {
    return new Image(readUrl(url), width, height, preserveRatio, smooth);
  }

  public static ImageView getImageView(String url, double width,
                       double height, boolean preserveRatio, boolean smooth) {
    return new ImageView(getImage(url, width, height, preserveRatio, smooth));
  }
}