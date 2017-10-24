package BlackJack.model.rules;

public interface IRulesVisitor {
	public void getRules(String NewGameStrategy, String HitRule, String WinRule);

}
