package edu.spbstu.blackjack.model.Card;

import org.jetbrains.annotations.Contract;

public class Card
{
  private final Suit suit;
  private final Face face;

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

  protected void flipCard()
  {
    visibility = !visibility;
  }
}
