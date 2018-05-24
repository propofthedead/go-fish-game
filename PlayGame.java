/*PlayGame class created by Foster Stulen on 5/4/18 for CSE 271
 * This class is to make it so we can play a game of go fish with
 * about four people with a begining hand of 7 cards and wish to make four
 * of kind or a book of a rank
 */

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;

public class PlayGame
{
  private LinkedList<Hand> playerHands= new LinkedList<Hand>();
  private DeckList funDeck= new DeckList();
  private int numPlayers=0;
  private int[] playerScores;
  private int handSize;
  
  public PlayGame()
  {
    
  }
  public PlayGame(int players, int handSize)
  {
    numPlayers=players;
    if (numPlayers>1 && numPlayers<5)
    {
      funDeck.shuffle();
      for (int i=0;i<numPlayers;i++)
      {
        //String namePlayer= "player"+ String.valueOf(i);
        Hand player= new Hand(handSize);
        playerHands.addLast(player);
      }
      this.dealHands(handSize);
    }
  }
  
  private void dealHands(int handSize)
  {
    for(int i=0;i<numPlayers;i++)
    {
      Hand temp= playerHands.get(i);
      for(int j=0;j<handSize;j++)
      {
        Card adding= funDeck.getCard();
        temp.addCard(adding);
      }
      playerHands.set(i,temp);
    }
  }
  
  private int getNumPlayers()
  {
    return numPlayers;
  }
  
  public Hand getHand(int playnum)
  {
    return playerHands.get(playnum);
  }
  
  private void showHand(int playnum)
  {
    Hand temp=playerHands.get(playnum);
    temp.showHand();
  }
  public boolean hasFourOfaKind(int playerNum)
  {
    Hand temp = playerHands.get(playerNum);
    for(int i=2;i<=14;i++)
    {
      int rankCount=0;
      for (int j=0;j<temp.getHandSize();j++)
      {
        Card tempCard=temp.getCard(j);
        if(i==tempCard.getRank())
        {
          rankCount++;
        }
      }
      if(rankCount==4)
      {
        this.removeFour(i,playerNum);
        return true;
      }
    }
    return false;
  }
  private void removeFour(int rank,int playernum)
  {
    Hand playHand= playerHands.get(playernum);
    for(int i=0;i<playHand.getHandSize();i++)
    {
      Card tempCard= playHand.getCard(i);
      if(tempCard.getRank()==rank)
      {
        try{
          playHand.removeCard(i);
        }
        catch(InvalidCardReference e)
        {
          System.out.println("error");
        }
      }
    }
  }
  public boolean hadWantedCard(int playNum,int rankWanted)
  {
    Hand temp= playerHands.get(playNum);
    for (int i=0;i<temp.getHandSize();i++)
    {
      Card tempCard= temp.getCard(i);
      if(rankWanted==tempCard.getRank())
      {
        return true;
      }
    }
    return false;
    
  }
  private void updateScore(int playernum)
  {
    int oldScore=playerScores[playernum];
    oldScore++;
    playerScores[playernum]=oldScore;
  }
  private void showScores()
  {
    for(int i=0;i<numPlayers;i++)
    {
      System.out.println(playerScores[i]);
    }
  }
  public String determineWinner()
  {
    if(playerScores[0]>playerScores[1] && playerScores[0]>playerScores[2] && playerScores[0]>playerScores[3])
    {
      return "Player one wins";
    }
    else if(playerScores[1]>playerScores[0] && playerScores[1]>playerScores[2] && playerScores[1]>playerScores[3])
    {
      return "Player two wins";
    }
    else if (playerScores[2]>playerScores[0] && playerScores[2]> playerScores[1] && playerScores[2]>playerScores[3])
    {
      return "Player three wins";
    }
    else if(playerScores[3]>playerScores[0] && playerScores[3]>playerScores[1] && playerScores[3]>playerScores[2])
    {
      return "Player four wins";
    }
    else
    {
      return "there is a tie";
    }
  }
  
  private void ScoreBook(int playnum)
  {
    
  }
  public void goFish(int playnum)
  {
    Hand temp= playerHands.get(playnum);
    temp.setHandSize((temp.getHandSize()));
    Card tempCard=funDeck.getCard();
    temp.addCard(tempCard);
    playerHands.set(playnum,temp);
  }
  
  public void exchangeCard(int play1, int play2, int rank) //throws Exception, InvalidCardReference
  {
    Hand playerRemove = playerHands.get(play1);
    Hand playerAdd= playerHands.get(play2);
    boolean flag= this.hadWantedCard(play1,rank);
    for(int i=0;i<playerRemove.getHandSize();i++)
    {
      Card temp=playerRemove.getCard(i);
      if(rank==temp.getRank())
      {
        try{
        playerAdd.setHandSize(playerAdd.getHandSize()+1);
        playerAdd.addCard(temp);
        playerRemove.removeCard(i);
        }
        catch(InvalidCardReference e)
        {
          System.out.println("error");
        }
     }
    }
    playerHands.set(play1,playerRemove);
    playerHands.set(play2,playerAdd);
    
  }
  public void setGame(int players, int handSize)
  {
    numPlayers=players;
    if (numPlayers>1 && numPlayers<5)
    {
      funDeck.shuffle();
      for (int i=0;i<numPlayers;i++)
      {
        //String namePlayer= "player"+ String.valueOf(i);
        Hand player= new Hand(handSize);
        playerHands.addLast(player);
      }
      this.dealHands(handSize);
    }
  }
  public int checkDeck()
  {
    return funDeck.getCardsLeft();
  }
  public static void main(String[] args)
  {
    PlayGame goFish= new PlayGame(2,4);
    
    System.out.println(goFish.getNumPlayers());
    goFish.showHand(0);
    goFish.showHand(1);
    Scanner in= new Scanner(System.in);
    System.out.println("Ask for a rank in the bottom of the list as a number");
    int rank= in.nextInt();
    System.out.println(goFish.hadWantedCard(1,rank));
  }
}