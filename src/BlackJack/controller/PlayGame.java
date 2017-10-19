package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.view.IView.Order;
import BlackJack.model.Game;
import BlackJack.model.IObserver;

public class PlayGame implements IObserver {
	
	private Game a_game;
	private IView a_view;
	
	public PlayGame(Game a_game, IView a_view) {
		this.a_game = a_game;
		this.a_view = a_view;
		a_game.AddListner(this);
	}

  public boolean Play() {
	
    a_view.DisplayWelcomeMessage();
    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

    if (a_game.IsGameOver())
    {
        a_view.DisplayGameOver(a_game.IsDealerWinner());
    }

    Order input = a_view.GetInput();
    
    if (input == Order.PLAY)
    {
        a_game.NewGame();
    }
    else if (input == Order.HIT)
    {
        a_game.Hit();
    }
    else if (input == Order.STAND)
    {
        a_game.Stand();
    }

    return input != Order.QUIT;
  }

@Override
public void PlayerGetNewCard() {
	try {
		Thread.sleep(2000);
		a_view.DisplayWelcomeMessage();
		a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
		a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

		if (a_game.IsGameOver()) {
			a_view.DisplayGameOver(a_game.IsDealerWinner());
		}
		
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
}

}