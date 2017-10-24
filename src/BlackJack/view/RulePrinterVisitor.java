package BlackJack.view;

import BlackJack.model.rules.*;

public class RulePrinterVisitor implements IRulesVisitor {
	String newGameStrategy;
	String hitRule;
	String winRule;

	public String getNewGameStrategy() {
		return newGameStrategy;
	}

	public void setNewGameStrategy(String newGameStrategy) {
		this.newGameStrategy = newGameStrategy;
	}

	public String getHitRule() {
		return hitRule;
	}

	public void setHitRule(String hitRule) {
		this.hitRule = hitRule;
	}

	public String getWinRule() {
		return winRule;
	}

	public void setWinRule(String winRule) {
		this.winRule = winRule;
	}

	@Override
	public void getRules(String NewGameStrategy, String HitRule, String WinRule) {
		this.setNewGameStrategy(NewGameStrategy);
		this.setHitRule(HitRule);
		this.setWinRule(WinRule);
	}
}