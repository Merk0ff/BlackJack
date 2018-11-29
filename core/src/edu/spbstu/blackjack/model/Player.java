package edu.spbstu.blackjack.model;

import edu.spbstu.blackjack.model.Card.*;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

public final class Player
{
  private ArrayList<Card> cards;
  private Integer money;
  private Integer wins;
  private Integer gamePlayed;

  public Player(Integer M)
  {
    money = M;
  }

  public Card takeCard(CardPool C)
  {
    return C.getCard();
  }

  public void endTurn()
  {
    gamePlayed++;
  }

  public void roundEnd(boolean Win)
  {
    wins += Win ? 1 : 0;
  }

  public void changeMoney(Integer Amount)
  {
    money += Amount;
  }

  @Contract(pure = true)
  public ArrayList<Card> getCards()
  {
    return cards;
  }

  @Contract(pure = true)
  public Integer getMoney()
  {
    return money;
  }

  @Contract(pure = true)
  public Double getWinRate()
  {
    return (double)wins / gamePlayed;
  }
}
