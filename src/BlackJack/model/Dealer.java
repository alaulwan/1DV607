package BlackJack.model;

import BlackJack.model.rules.*;

public class Dealer extends Player {

	private Deck m_deck;
	private INewGameStrategy m_newGameRule;
	private IHitStrategy m_hitRule;
	private IWinStrategy m_winRule;

	public Dealer(RulesFactory a_rulesFactory) {

		m_newGameRule = a_rulesFactory.GetNewGameRule();
		m_winRule = a_rulesFactory.GetWinRule();
		m_hitRule = a_rulesFactory.GetHitRule();

		/*
		 * for(Card c : m_deck.GetCards()) { c.Show(true); System.out.println("" +
		 * c.GetValue() + " of " + c.GetColor()); }
		 */
	}

	public boolean NewGame(Player a_player) {
		if (m_deck == null || IsGameOver()) {
			m_deck = new Deck();
			ClearHand();
			a_player.ClearHand();
			return m_newGameRule.NewGame(m_deck, this, a_player);
		}
		return false;
	}

	public boolean Hit(Player a_player) {
		if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {
			CardFromDeckToPlayer(a_player, true);

			return true;
		}
		return false;
	}

	public boolean IsDealerWinner(Player a_player) {
		return m_winRule.IsDealerWinner(a_player, this, g_maxScore);

	}

	public boolean IsGameOver() {
		if (m_deck != null && m_hitRule.DoHit(this) != true) {
			return true;
		}
		return false;
	}

	public boolean Stand() {
		if (m_deck != null) {
			ShowHand();
			while (m_hitRule.DoHit(this)) {
				CardFromDeckToPlayer(this, true);
			}
			return true;
		}
		return false;
	}

	public void CardFromDeckToPlayer(Player a_player, boolean isCardShowed) {
		Card c = m_deck.GetCard();
		c.Show(isCardShowed);
		a_player.DealCard(c);
	}

}