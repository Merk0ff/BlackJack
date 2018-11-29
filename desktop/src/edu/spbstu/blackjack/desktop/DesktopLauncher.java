package edu.spbstu.blackjack.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.spbstu.blackjack.BlackJack;

public class DesktopLauncher
{
  public static void main(String[] arg)
  {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Super ultimate Black Jack"; config.height = BlackJack.WINDOW_W; config.height = BlackJack.WINDOW_H;
    new LwjglApplication(new BlackJack(), config);
  }
}
