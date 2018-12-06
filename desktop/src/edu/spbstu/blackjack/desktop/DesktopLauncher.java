package edu.spbstu.blackjack.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.spbstu.blackjack.BlackJack;

public class DesktopLauncher
{
  public static void main(String[] arg)
  {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Super ultimate mega ultra jesus combo Black Jack"; config.height = 960; config.height = 540;
    config.resizable = false;

    String os = System.getProperty("os.name").toLowerCase();

    if (os.contains("win"))
      config.addIcon("pizdos32.png", Files.FileType.Internal );
    else if (os.contains("mac"))
      config.addIcon("pizdos128.png", Files.FileType.Internal );




    new LwjglApplication(new BlackJack(), config);
  }
}
