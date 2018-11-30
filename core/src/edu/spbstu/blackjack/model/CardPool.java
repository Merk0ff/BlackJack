package edu.spbstu.blackjack.model;

import edu.spbstu.blackjack.model.Card.Card;
import edu.spbstu.blackjack.model.Card.Face;
import edu.spbstu.blackjack.model.Card.Suit;

import java.util.ArrayList;

/**
 * CardPool is the class that contains everything about current cards in current round
 *
 * @author      Dukshtau Philip
 * @version     %I%, %G%
 * @since       1.0
 */
public class CardPool
{
  private ArrayList<Card> cards; // cards in current round

  /**
   * This method fill cards array.
   * ATTENTION!!! this method dont call clear() method
   */
  private void fill()
  {
    for (int j = 0; j < Suit.values().length; j++)
      for (int i = 0; i < Face.values().length; i++)
        cards.add(new Card(Suit.values()[j], Face.values()[i]));
  }

  /**
   * This is the constructor of card.
   * It fills card array for current round
   */
  public CardPool()
  {
    cards = new ArrayList<Card>();

    fill();
  }

  /**
   * This method shuffle cards
   * It means that it clear() current cards and then refill it
   */
  public void shuffle()
  {
    cards.clear();
    fill();
  }

  /**
   * This method return a random card from current cards array
   * @return Random card
   */
  public Card getCard()
  {
    return cards.remove((int)(Math.random() * cards.size()));
  }
}
