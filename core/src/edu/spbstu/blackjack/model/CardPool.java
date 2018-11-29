package edu.spbstu.blackjack.model;

import edu.spbstu.blackjack.model.Card.Card;
import edu.spbstu.blackjack.model.Card.Face;
import edu.spbstu.blackjack.model.Card.Suit;

import java.util.ArrayList;

public class CardPool
{
  private ArrayList<Card> Cards;

  private void fill()
  {
    for (int j = 0; j < Suit.values().length; j++)
      for (int i = 0; i < Face.values().length; i++)
        Cards.add(new Card(Suit.values()[j], Face.values()[i]));
  }

  public CardPool()
  {
    Cards = new ArrayList<Card>();

    fill();
  }

  public void shuffle()
  {
    Cards.clear();
    fill();
  }

  public Card getCard()
  {
    return Cards.remove((int)(Math.random() * Face.values().length * Suit.values().length));
  }
}
