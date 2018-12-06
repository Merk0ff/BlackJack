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

/**
 * Core unit class
 *
 * @author      Dukshtau Philip
 * @version     %I%, %G%
 * @since       1.0
 */
public class BlackJack extends ApplicationAdapter
{
  private SpriteBatch batch;  // Here we draw
  private BitmapFont font;    // Font texture
  private Viewport viewPort;  // View port
  private int WINDOW_W = 960; // Current window w
  private int WINDOW_H = 540; // Current window H

  private static final int SCALEFACTOR = 4;

  private Texture cardsTexture[]; // Static array of textures
  private Texture cardBack;       // Background card texture

  private CardPool cardPool = new CardPool();  // Card pool of the round
  private final static int startMoney = 500;   // Static start money

  private boolean pause = false;    // Pause state variable
  private boolean setPause = false; // Set or unset pause
  private long time = 0;

  // Round state
  // 3 - Ai time
  // 2 - in game
  // 10 - you win Logic KOSTIL
  // 1 - you win
  // 0 - round restart
  // -1 - you lose
  // -10 - you lose Logic KOSTIL
  // -2 - bet time
  // -3 - game over
  // -30 - Kostil
  private Integer roundState;

  // Current round bet
  private Integer betPlaced = 0;

  private Player dealer = new Player(0),   // Dealer unit
          gamer = new Player(startMoney);      // gamer unit

  /**
   * Load textures from file
   */
  private void loadData()
  {
    font = new BitmapFont();

    cardsTexture = new Texture[54];
    cardBack = new Texture("card_back.png");

    for (int j = 0; j < Suit.values().length; j++)
      for (int i = 2; i <= 10; i++)
        cardsTexture[j * 13 + i - 2] = new Texture(i + "_of_" + Suit.values()[j].name().toLowerCase() + ".png");

    for (int j = 0; j < Suit.values().length; j++)
      for (int i = 9; i <= 12; i++)
        cardsTexture[j * 13 + i] = new Texture(Face.values()[i].name().toLowerCase()
                                                 + "_of_" + Suit.values()[j].name().toLowerCase() + ".png");

  }

  /**
   * JDX create override
   */
  @Override
  public void create()
  {
    loadData();

    roundState = -2;

    viewPort = new FitViewport(WINDOW_W, WINDOW_H);
    batch = new SpriteBatch();
    Gdx.graphics.setDisplayMode(WINDOW_W, WINDOW_H, false);
  }

  /**
   * Render current hands
   */
  private void renderHands()
  {
    // Render Player cards
    for (int i = 0; i < gamer.getCards().size(); i++)
      if (gamer.getCards().get(i).isVisibility())
      {
        Card c = gamer.getCards().get(i);

        Texture cT = cardsTexture[c.getFace().ordinal() + c.getSuit().ordinal() * 13];
        try
        {
          batch.draw(cT, cT.getWidth() / SCALEFACTOR * i, 0, cT.getWidth() / SCALEFACTOR, cT.getHeight() / SCALEFACTOR);
        }
        catch (Exception e)
        {
          System.out.println(c.getFace());
        }
      }
      else
      {
        batch.draw(cardBack, cardBack.getWidth() / SCALEFACTOR * i, 0, cardBack.getWidth() / SCALEFACTOR, cardBack.getHeight() / SCALEFACTOR);
      }

    // Render dealer cards
    for (int i = 0; i < dealer.getCards().size(); i++)
      if (dealer.getCards().get(i).isVisibility())
      {
        Card c = dealer.getCards().get(i);

        Texture cT = cardsTexture[c.getFace().ordinal() + c.getSuit().ordinal() * 13];

        try
        {
          //font.draw(batch, c.getFace().name() + " " + c.getSuit().name(), cT.getWidth() * i / 3, cT.getHeight() / 3 + 30);
          batch.draw(cT, cT.getWidth() / SCALEFACTOR * i, WINDOW_H - cT.getHeight() / SCALEFACTOR,
                  cT.getWidth() / SCALEFACTOR, cT.getHeight() / SCALEFACTOR);
        }
        catch (Exception e)
        {
          System.out.println(c.getFace());
        }
      }
      else
      {
        batch.draw(cardBack, cardBack.getWidth() / SCALEFACTOR * i, WINDOW_H - cardBack.getHeight() / SCALEFACTOR,
                cardBack.getWidth() / SCALEFACTOR, cardBack.getHeight() / SCALEFACTOR);
      }
  }

  /**
   * JDX render override
   */
  @Override
  public void render()
  {
    Gdx.gl.glClearColor(0.3f, 0.5f, 0.7f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    font.setColor(Color.BLACK);

    batch.begin();

    if (setPause)
    {
      setPause = false;
      pause = true;
      time = System.currentTimeMillis();
    }

    if (pause && System.currentTimeMillis() >= time + 2000)
      pause = false;

    if (roundState == 2)
    {
      renderHands();

      font.draw(batch, "Your card sum: " + gamer.getCardSum(), WINDOW_W / 2 - 30, WINDOW_H - 30);
      font.draw(batch, "Dealer card sum: " + dealer.getCardSum(), WINDOW_W / 2 - 30, WINDOW_H - 60);
      font.draw(batch, "Your bet: " + betPlaced, WINDOW_W / 2 - 30, WINDOW_H - 90);
    }
    else if (roundState == 3)
    {
      renderHands();

      font.draw(batch, "Your card sum: " + gamer.getCardSum(), WINDOW_W / 2 - 30, WINDOW_H - 30);
      font.draw(batch, "Dealer card sum: " + dealer.getCardSum(), WINDOW_W / 2 - 30, WINDOW_H - 60);
      font.draw(batch, "Your bet: " + betPlaced, WINDOW_W / 2 - 30, WINDOW_H - 90);
    }
    else if (roundState == -2)
    {
      font.draw(batch, "Your have: " + gamer.getMoney(), WINDOW_W / 2, WINDOW_H / 2 + 30);
      font.draw(batch, "Your bet: " + betPlaced, WINDOW_W / 2, WINDOW_H / 2 + 10);
    }
    else if (roundState == -1)
    {
      font.draw(batch, "YOU LOSE", WINDOW_W / 2, WINDOW_H / 2 + 30);
      font.draw(batch, "Your have: " + gamer.getMoney(), WINDOW_W / 2, WINDOW_H / 2 + 10);
      font.draw(batch, "Your win rate: " + gamer.getWinRate(), WINDOW_W / 2, WINDOW_H / 2 - 10);
      font.draw(batch, "R for the next round", WINDOW_W / 2, WINDOW_H / 2 - 30);
    }
    else if (roundState == 1)
    {
      font.draw(batch, "YOU WIN", WINDOW_W / 2, WINDOW_H / 2 + 30);
      font.draw(batch, "Your have: " + gamer.getMoney(), WINDOW_W / 2, WINDOW_H / 2 + 10);
      font.draw(batch, "Your win rate: " + gamer.getWinRate(), WINDOW_W / 2, WINDOW_H / 2 - 10);
      font.draw(batch, "R for the next round", WINDOW_W / 2, WINDOW_H / 2 - 30);
    }
    else if (roundState == -3)
    {
      font.draw(batch, "GAME OVER", WINDOW_W / 3, WINDOW_H / 2 + 30);
      font.draw(batch, "Your win rate: " + gamer.getWinRate(), WINDOW_W / 3, WINDOW_H / 2 - 10);
      font.draw(batch, "Your high score rate: " + gamer.getHighScore(), WINDOW_W / 3, WINDOW_H / 2 - 30);
      font.draw(batch, "Press R to restart", WINDOW_W / 3, WINDOW_H / 2 + 10);
    }

    batch.end();

    controlHandle();
    gameLogic();
  }

  /**
   * Control handle
   */
  private void controlHandle()
  {
    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ||
            Gdx.input.isKeyPressed(Input.Keys.SYM) && Gdx.input.isKeyJustPressed(Input.Keys.Q))
    {
      batch.end();
    }
    switch (roundState)
    {
      // Bet placement stage
      case -2:
        if (Gdx.input.isKeyJustPressed(Input.Keys.W) && gamer.getMoney() >= betPlaced + 100)
          betPlaced += 100;
        else if (Gdx.input.isKeyJustPressed(Input.Keys.S) && betPlaced >= 100)
          betPlaced -= 100;
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && betPlaced > 0)
          roundState = 0;
        break;

      // Game stage
      case 2:
        if (Gdx.input.isKeyJustPressed(Input.Keys.W))
        {
          gamer.takeCard(cardPool);
          gamer.getCards().get(gamer.getCards().size() - 1).flipCard();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
          roundState = 3;
        break;

      case -3:
        if (Gdx.input.isKeyJustPressed(Input.Keys.R))
          roundState = -30;
        break;
      case 1:
      case -1:
        if (Gdx.input.isKeyJustPressed(Input.Keys.R))
          roundState = -2;
        break;
    }
  }

  private void gameLogic()
  {
    switch (roundState)
    {
      case 0:
       cardPool.shuffle();

        dealer.takeCard(cardPool);
        dealer.takeCard(cardPool);

        dealer.getCards().get(0).flipCard();

        gamer.takeCard(cardPool, true);
        gamer.takeCard(cardPool, true);

        roundState = 2;

        break;

      case 2:
        if (gamer.getCardSum() > 21)
          roundState = -10;
        break;

      case 3:
        if (pause)
          return;

        for (Card c : dealer.getCards())
          if (!c.isVisibility())
          {
            c.flipCard();
            setPause = true;
            return;
          }

        if (dealer.getCardSum() <= 17 && dealer.getCardSum() < gamer.getCardSum())
        {
          dealer.takeCard(cardPool, true);
          setPause = true;
        }
        else if (dealer.getCardSum() > 21)
          roundState = 10;
        else if (dealer.getCardSum() > gamer.getCardSum())
          roundState = -10;
        else
          roundState = 10;

        break;

      case 10:
        dealer.roundEnd(false);
        gamer.changeMoney(betPlaced);
        betPlaced = 0;
        gamer.roundEnd(true);
        roundState = 1;
        break;

      case -10:
        dealer.roundEnd(true);
        gamer.changeMoney(-betPlaced);
        betPlaced = 0;
        gamer.roundEnd(false);

        if (gamer.getMoney() <= 0)
          roundState = -3;
        else
          roundState = -1;

        break;
      case -30:
        gamer.gameOver(500);
        roundState = -2;
        break;
    }
  }
}
