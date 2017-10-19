package BlackJack.view;

import BlackJack.model.Game;

public interface IView {
	public enum Order {
		PLAY, HIT, STAND, QUIT
	}

	void DisplayWelcomeMessage();
	void DisplayRules(Game a_game);
	
	Order GetInput();

	void DisplayCard(BlackJack.model.Card a_card);

	void DisplayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score);

	void DisplayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score);

	void DisplayGameOver(boolean a_dealerIsWinner);

	
}