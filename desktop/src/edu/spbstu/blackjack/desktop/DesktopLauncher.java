package edu.spbstu.blackjack.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.spbstu.blackjack.BlackJack;

public class DesktopLauncher
{
  public static void main(String[] arg)
  {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Super ultimate Black Jack"; config.height = 960; config.height = 540;
    new LwjglApplication(new BlackJack(), config);
  }
}
