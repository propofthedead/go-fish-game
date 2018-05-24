import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
public class GoFishGUI extends JPanel //throws Exception
{
  JPanel start= new JPanel();
  JPanel cardHolder= new JPanel();
  JLabel rules= new JLabel();
  JTextPane startPlayers= new JTextPane();
  JTextPane startHand= new JTextPane();
  JPanel game= new JPanel();
  JTextPane askRank= new JTextPane();
  JButton askButton= new JButton();
  JButton playButton = new JButton();
  //JLabel playerOne= new JLabel();
  //JLabel playerTwo = new JLabel();
  //JLabel playerThree = new JLabel();
  //JLabel playerFour= new JLabel();
  //JLabel answer= new JLabel();
  JPanel starter= new JPanel();
  JTextPane askPlayer= new JTextPane();
  JLabel player= new JLabel();
  JLabel rank= new JLabel();
 // Card labels
  JLabel playercard1;
  JLabel playercard2;
  JLabel playercard3;
  JLabel playercard4;
  JLabel playercard5;
  JLabel playercard6;
  JLabel playercard7;
  JLabel playercard8;
  JLabel playercard9;
  JLabel playercard10;
  JLabel playercard11;
  JLabel playercard12;
  
  
  int playerAsk=0;
  int players;
  int handsize;
  PlayGame goFish= new PlayGame();
  public GoFishGUI()
  {
    game.setBackground( new Color(0,100,50));
    game.setLayout(new FlowLayout());
    
    cardHolder.setBackground(new Color(122,0,0));
    setSize(600,600);
    //setTitle("Go Fish");
    askButton.setText(" Do you have any?");
    askButton.addActionListener(new askButton());
    rules.setText("Please enter the amount of player 2-4, then pick a handsize \n"+
                  "The rules are simple. The objective is make to make books \n"+
                  "of the same rank of cards across the four suits. You will be \n"+
                  "calling ranks as such in the textbox down below, TWO=2,THREE=3,FOUR=4 \n"+
                  "FIVE=5, SIX=6, SEVEN=7, EIGHT=8,NINE=9,TEN=10,JACK=11,QUEEN=12,KING=13 \n"+
                  "while ACE=14. The game will end when the deck is empty.");
    
    playButton.setText("Let's play");
    playButton.addActionListener(new playButton());
    askButton.setEnabled(false);
    rank.setText("Rank of card");
    askPlayer.setText("which player to ask");


//put the starter stuff in the top part of it
    starter.add(startPlayers);
    starter.add(startHand);
    starter.add(playButton);
    //adds the game into it
    game.add(rules);
    game.add(rank);
    game.add(askRank);
    game.add(player);
    game.add(askPlayer);
    game.add(askButton);
    //holds 12 label panals 
    cardHolder.add(playercard1);
    cardHolder.add(playercard2);
    cardHolder.add(playercard3);
    cardHolder.add(playercard4);
    cardHolder.add(playercard5);
    cardHolder.add(playercard6);
    cardHolder.add(playercard7);
    cardHolder.add(playercard8);
    cardHolder.add(playercard9);
    cardHolder.add(playercard10);
    cardHolder.add(playercard11);
    cardHolder.add(playercard12);
    
    setLayout(new BorderLayout());
    add(starter, BorderLayout.NORTH);
    add(game, BorderLayout.CENTER);
    add(cardHolder, BorderLayout.SOUTH);
  }
  
  public void display()
  {
    JFrame myFrame= new JFrame("Go Fish");
    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myFrame.setContentPane(this);
    myFrame.setPreferredSize(new Dimension(700,550));
    
    myFrame.pack();
    myFrame.setVisible(true);
  }
  
  
  class playButton implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
       players=Integer.parseInt(startPlayers.getText());
       handsize= Integer.parseInt(startHand.getText());
      PlayGame goFish= new PlayGame(players,handsize);
      playButton.setEnabled(false);
      askButton.setEnabled(true);
      //int countTurn=0;
      Hand player= goFish.getHand(playerAsk);
      Card temp=player.getCard(0);
      playercard1.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
      temp=player.getCard(1);
      playercard2.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
       temp=player.getCard(2);
      playercard3.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
      temp=player.getCard(3);
      playercard4.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
      
      temp=player.getCard(4);
      playercard5.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
      
      temp=player.getCard(5);
      playercard6.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
    
       temp=player.getCard(6);
      playercard7.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
    
     temp=player.getCard(7);
      playercard8.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
     temp=player.getCard(8);
      playercard9.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
    
     temp=player.getCard(9);
      playercard10.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
      
       temp=player.getCard(10);
      playercard11.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
    
       temp=player.getCard(11);
      playercard12.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
    }
  }
  class askButton implements ActionListener 
  {
    public void actionPerformed(ActionEvent e) 
    {
      int rank=Integer.parseInt(askRank.getText());
      int playerCounter=Integer.parseInt(askPlayer.getText())-1;
      boolean test=goFish.hadWantedCard(playerCounter,rank);
      if(test==false)
      {
        goFish.goFish(playerAsk);
      }
      while(test==true) 
      {
        //try{
          
        goFish.exchangeCard(playerCounter,playerAsk,rank);
        //throw new InvalidCardReference();
        //}
        //catch(InvalidCardReference de)
        //{
          //System.out.println("there is no card");
          //return;
        test=goFish.hadWantedCard(playerCounter,rank);
      }
      boolean score=goFish.hasFourOfaKind(playerAsk);
      
      if(goFish.checkDeck()==0){
      if(playerAsk==(players-1))
      {
        playerAsk=0;
      }
      else
      {
        playerAsk++;
      }
      
      Hand player= goFish.getHand(playerAsk);
      Card temp=player.getCard(0);
      playercard1.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
      temp=player.getCard(1);
      playercard2.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
       temp=player.getCard(2);
      playercard3.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
      temp=player.getCard(3);
      playercard4.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
      
      temp=player.getCard(4);
      playercard5.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
      
      temp=player.getCard(5);
      playercard6.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
    
       temp=player.getCard(6);
      playercard7.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
    
     temp=player.getCard(7);
      playercard8.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
     temp=player.getCard(8);
      playercard9.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
    
     temp=player.getCard(9);
      playercard10.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
      
       temp=player.getCard(10);
      playercard11.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
    
       temp=player.getCard(11);
      playercard12.setText("rank: "+temp.getRankAsString()+"\n  Suit: "+temp.getSuitAsString());
      }
      else
      {
        String winnerwinner= goFish.determineWinner();
        playercard1.setText(winnerwinner);
      }
    }
  }
  
  
  public static void main(String args[])
  {
    GoFishGUI simpleUI = new GoFishGUI(); 
     
    simpleUI.setVisible(true); 
  }
}