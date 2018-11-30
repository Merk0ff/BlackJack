package edu.spbstu.blackjack.model;

import edu.spbstu.blackjack.model.Card.*;

import java.util.ArrayList;

/**
 * Player is the class that contains everything about player
 * Such as current cards, count of wins, current money, count of played games
 *
 * @author      Dukshtau Philip
 * @version     %I%, %G%
 * @since       1.0
 */
public final class Player
{
  private ArrayList<Card> cards; // Current round cards
  private Integer money;         // Current money
  private Integer wins;          // Wins count
  private Integer gamePlayed;    // Count of played games
  private Integer highScore;     // High score

  /**
   * This is the constructor of player.
   * @param M Starting money.
   */
  public Player(Integer M)
  {
    money = M;
    gamePlayed = 0;
    wins = 0;
    highScore = M;
    cards = new ArrayList<Card>();
  }

  /**
   * This method get a card to cards from card pool.
   * @param C This is a current round card pool.
   */
  public void takeCard(CardPool C)
  {
    cards.add(C.getCard());
  }
  /**
   * This method get a card to cards from card pool.
   * @param C This is a current round card pool.
   * @param flipped Flipped or not
   */
  public void takeCard(CardPool C, Boolean flipped)
  {
    Card tmp = C.getCard();

    if (tmp.isVisibility() != flipped)
      tmp.flipCard();

    cards.add(tmp);
  }

  /**
   * This method get a card to cards from card pool.
   * @param Win This is a state that means win this player or not.
   */
  public void roundEnd(boolean Win)
  {
    wins += Win ? 1 : 0;
    gamePlayed++;

    if (money > highScore)
      highScore = money;

    // Clear cards
    cards.clear();
  }

  /**
   * This method change current money of the player.
   * @param Amount This is delta of money change, can be positive or negative.
   */
  public void changeMoney(Integer Amount)
  {
    money += Amount;
  }

  /**
   * This method change current money of the player.
   * @return current player cards
   */
  public final ArrayList<Card> getCards()
  {
    return cards;
  }

  /**
   * This method change current money of the player.
   * @return current visible card sum.
   */
  public Integer getCardSum()
  {
    Integer sum = 0;

    for (Card c : cards)
      if (c.isVisibility())
        sum += c.getFace().ordinal() + 2;

    return sum;
  }

  /**
   * This method change current money of the player.
   * @return current player money.
   */
  public Integer getMoney()
  {
    return money;
  }

  /**
   * This method change current money of the player.
   * @return high score.
   */
  public Integer getHighScore()
  {
    return highScore;
  }

  /**
   * This method change current money of the player.
   * @return player win rate.
   */
  public Double getWinRate()
  {
    return (double)wins / gamePlayed;
  }
}
