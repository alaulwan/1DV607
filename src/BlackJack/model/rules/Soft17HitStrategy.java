package BlackJack.model.rules;

import BlackJack.model.Player;

class Soft17HitStrategy implements IHitStrategy{
	
	private final int g_hitLimit = 17;

	public boolean DoHit(Player a_dealer) {
		int Score = a_dealer.CalcScore();
		int ScoreWithLowAce = a_dealer.ScoreWithLowAce();
		
		if (Score-ScoreWithLowAce==9 &&  ScoreWithLowAce <= g_hitLimit)
			return true;
		
		return a_dealer.CalcScore() < g_hitLimit;
	}
    
    
}
