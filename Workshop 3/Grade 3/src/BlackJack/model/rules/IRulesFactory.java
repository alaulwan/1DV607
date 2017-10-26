package BlackJack.model.rules;

public interface IRulesFactory {
	public INewGameStrategy GetNewGameRule();

	public IHitStrategy GetHitRule();

	public IWinStrategy GetWinRule();

	public void Accept(IRulesVisitor a_visitor);
}