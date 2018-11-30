package edu.spbstu.blackjack.model.Card;

/**
 * Card is the class that contains everything about card abstraction
 * Such as Face, Suit and its visibility
 *
 * @author      Dukshtau Philip
 * @version     %I%, %G%
 * @since       1.0
 */
public class Card
{
  private final Suit suit;    // Suit of current card
  private final Face face;    // Face of current card
  private boolean visibility; // Visibility of the card

  /**
   * This is the constructor of card.
   *
   * Also default visibility is false.
   * @param S Suit that will have this card.
   * @param V Face that will have this card.
   */
  public Card(Suit S, Face V)
  {
    suit = S;
    face = V;
    visibility = false;
  }

  /**
   * This method returns suit of current card.
   * @return Suit.
   */
  public Suit getSuit()
  {
    return suit;
  }

  /**
   * This method returns face of current card.
   * @return Face.
   */
  public Face getFace()
  {
    return face;
  }

  /**
   * This method returns visibility of this card.
   * @return visibility.
   */
  public boolean isVisibility()
  {
    return visibility;
  }

  /**
   * This method change state of visibility.
   */
  public void flipCard()
  {
    visibility = !visibility;
  }
}
