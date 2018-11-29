package edu.spbstu.blackjack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.spbstu.blackjack.model.Card.Card;
import edu.spbstu.blackjack.model.Card.Face;
import edu.spbstu.blackjack.model.Card.Suit;
import edu.spbstu.blackjack.model.CardPool;
import edu.spbstu.blackjack.model.Player;

import javax.xml.soap.Text;
import java.util.ArrayList;

public class BlackJack extends ApplicationAdapter
{
  SpriteBatch batch;
  ArrayList<Texture> cardsTexture;
  Texture backgroundTexture;
  ArrayList<Texture> controlsTexture;
  ArrayList<Texture> stateTextures;
  BitmapFont font;

  Viewport viewPort;
  final static int WINDOW_W = 960;
  final static int WINDOW_H = 540;

  final static int startMoney = 500;

  Player dealer = new Player(0), gamer = new Player(startMoney);
  CardPool cardPool = new CardPool();
  Integer highScore = startMoney;

  private void loadData()
  {
    font = new BitmapFont();

    cardsTexture = new ArrayList<Texture>();
    controlsTexture = new ArrayList<Texture>();
    stateTextures = new ArrayList<Texture>();

    for (int j = 0; j < Suit.values().length; j++)
      for (int i = 2; i <= 10; i++)
        cardsTexture.add(new Texture(i + "_of_" + Suit.values()[j].name().toLowerCase() + ".png"));

    for (int j = 0; j < Suit.values().length; j++)
      for (int i = 9; i <= 12; i++)
        cardsTexture.add(new Texture(Face.values()[i].name().toLowerCase()
                                     + "_of_" + Suit.values()[j].name().toLowerCase() + ".png"));

//    backgroundTexture = new Texture("backgroungTexture.gif");
//
//    for (int i = 0; i < 4; i++)
//      controlsTexture.add(new Texture("controlTexture_" + i + ".gif"));
  }

  @Override
  public void create()
  {
    loadData();
    viewPort = new FitViewport(WINDOW_W, WINDOW_H);
    batch = new SpriteBatch();
    Gdx.graphics.setDisplayMode(WINDOW_W, WINDOW_H, false);
  }


  @Override
  public void render()
  {
    Gdx.gl.glClearColor(0.3f, 0.5f, 0.7f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    controlHandle();
    font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    font.setColor(Color.BLACK);
    batch.end();
    //for (int i = 0; i < cardsTexture.size(); i++)
    //  batch.draw(cardsTexture.get(i), cardsTexture.get(i).getWidth() * i, 0);

  }

  public void controlHandle()
  {
    //if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
    font.draw(batch, "Hello World!", WINDOW_W / 2, WINDOW_H / 2);
  }

  @Override
  public void resize(int width, int height)
  {
    viewPort.update(width, height);
  }

  private void gameLogic()
  {
    if (gamer.getMoney() > highScore)
      highScore = gamer.getMoney();
  }
}
