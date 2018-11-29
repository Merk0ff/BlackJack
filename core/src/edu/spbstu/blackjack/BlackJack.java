package edu.spbstu.blackjack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
  private SpriteBatch batch;
  //private ArrayList<Texture> cardsTexture;
  private Texture cardsTexture[];
  private Texture cardBack;
  private Texture backgroundTexture;
  private ArrayList<Texture> controlsTexture;
  private ArrayList<Texture> stateTextures;

  private ArrayList<Card> drawingCards;
  private CardPool cardPool = new CardPool();
  private BitmapFont font;

  private Viewport viewPort;
  public final static int WINDOW_W = 960;
  public final static int WINDOW_H = 540;

  private final static int startMoney = 500;

  private Player dealer = new Player(0), gamer = new Player(startMoney);
 // CardPool cardPool = new CardPool();
  private Integer highScore = startMoney;

  private void loadData()
  {
    font = new BitmapFont();

    cardsTexture = new Texture[54];
    controlsTexture = new ArrayList<Texture>();
    stateTextures = new ArrayList<Texture>();
    cardBack = new Texture("card_back.png");
    drawingCards = new ArrayList<Card>();

    for (int j = 0; j < Suit.values().length; j++)
      for (int i = 2; i <= 10; i++)
        cardsTexture[j * 13 + i - 2] = new Texture(i + "_of_" + Suit.values()[j].name().toLowerCase() + ".png");

    for (int j = 0; j < Suit.values().length; j++)
      for (int i = 9; i <= 12; i++)
        cardsTexture[j * 13 + i] = new Texture(Face.values()[i].name().toLowerCase()
                                                 + "_of_" + Suit.values()[j].name().toLowerCase() + ".png");

//    backgroundTexture = new Texture("backgroungTexture.gif");
//
//    for (int i = 0; i < 4; i++)
//      controlsTexture.add(new Texture("controlTexture_" + i + ".gif"));
  }

  @Override
  public void create()
  {
    loadData();
    //drawingCards = new ArrayList<Card>();
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

    for (Card c : drawingCards)
      if (c.isVisibility())
      {
        Texture cT = cardsTexture[c.getFace().ordinal() + c.getSuit().ordinal() * 13];
        try
        {
        font.draw(batch, c.getFace().name() + " " + c.getSuit().name(), cT.getWidth() / 2, cT.getHeight() / 2 + 30);
        batch.draw(cT, 0, 0, cT.getWidth() / 2, cT.getHeight() / 2);
        }
        catch (Exception e)
        {
          System.out.println(c.getFace());
        }
      }
      else
      {
        batch.draw(cardBack, 0, 0, cardBack.getWidth() / 2, cardBack.getHeight() / 2);
      }

    batch.end();
  }

  private void controlHandle()
  {
    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
    {
      batch.end();
    }
    else if (Gdx.input.isKeyJustPressed(Input.Keys.F))
    {
//      Card index = cardPool.getCard();
//      drawingCards.add(index);
      drawingCards.get(drawingCards.size() - 1).flipCard();
    }
    else if (Gdx.input.isKeyJustPressed(Input.Keys.D))
    {
      drawingCards.remove(drawingCards.size() - 1);
    }
    else if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))
    {
      Card c = cardPool.getCard();
      drawingCards.add(c);
    }
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
