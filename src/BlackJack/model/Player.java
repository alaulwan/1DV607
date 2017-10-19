package BlackJack.model;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Player {

  private List<Card> m_hand;
  private List<IObserver> m_listners;
  protected final int g_maxScore = 21;

  public Player()
  {
  
    m_hand = new LinkedList<Card>();
    m_listners = new ArrayList<IObserver>();
    System.out.println("Hello List World");
  }
  
  public void DealCard(Card a_addToHand)
  {
    m_hand.add(a_addToHand);
    NotifyListners();
  }
  
  public Iterable<Card> GetHand()
  {
    return m_hand;
  }
  
  public void ClearHand()
  {
    m_hand.clear();
  }
  
  public void ShowHand()
  {
    for(Card c : m_hand)
    {
      c.Show(true);
    }
  }
  
  public int CalcScore()
  {
    // the number of scores is dependant on the number of scorable values
    // as it seems there is no way to do this check at compile time in java ?!
    // cardScores[13] = {...};
    int cardScores[] = {
        2, 3, 4, 5, 6, 7, 8, 9, 10, 10 ,10 ,10, 11
    };
    assert (cardScores.length == Card.Value.Count.ordinal()) : "Card Scores array size does not match number of card values";
  
    
    int score = 0;

    for(Card c : GetHand()) {
        if (c.GetValue() != Card.Value.Hidden)
        {
            score += cardScores[c.GetValue().ordinal()];
        }
    }

    if (score > g_maxScore)
    {
        for(Card c : GetHand())
        {
            if (c.GetValue() == Card.Value.Ace && score > g_maxScore)
            {
                score -= 10;
            }
        }
    }

    return score;
  }
  
  public int ScoreWithLowAce()
  {
	  	int numberOfAce = 0;
		int ScoreWithLowAce = CalcScore();
		for (Card c : GetHand()) {
			if (c.GetValue() == Card.Value.Ace) {
				numberOfAce++;
			}
		}
		if (numberOfAce>0 && ScoreWithLowAce + numberOfAce*10 < 22)
			ScoreWithLowAce-=10;
		return ScoreWithLowAce;
  }

  public void AddListner(IObserver a_subscriber){
	  m_listners.add(a_subscriber);
	}
  
  private void NotifyListners(){
		for(IObserver a_subscriber: m_listners){
			a_subscriber.PlayerGetNewCard();
		}
	}
  
}