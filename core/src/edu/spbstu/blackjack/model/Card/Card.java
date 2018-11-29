package edu.spbstu.blackjack.model.Card;

import com.badlogic.gdx.graphics.Texture;
import org.jetbrains.annotations.Contract;

public class Card
{
  private final Suit suit;
  private final Face face;

  // Here i dropped an idea about MVC
  //private final Texture texture;

  private boolean visibility;

  public Card(Suit S, Face V)
  {
    suit = S;
    face = V;
    visibility = false;
  }

  @Contract(pure = true)
  public Suit getSuit()
  {
    return suit;
  }

  @Contract(pure = true)
  public Face getFace()
  {
    return face;
  }

  @Contract(pure = true)
  public boolean isVisibility()
  {
    return visibility;
  }

//  public Texture getTexture()
//  {
//    return texture;
//  }

  public void flipCard()
  {
    visibility = !visibility;
  }
}
