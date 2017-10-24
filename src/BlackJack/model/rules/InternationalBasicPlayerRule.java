package BlackJack.model.rules;

class InternationalBasicPlayerRule implements IRulesFactory {

	@Override
	public INewGameStrategy GetNewGameRule() {
		return new InternationalNewGameStrategy();
	}

	@Override
	public IHitStrategy GetHitRule() {
		return new BasicHitStrategy();
	}

	@Override
	public IWinStrategy GetWinRule() {
		return new PlayerWinStrategy();
	}

	@Override
	public void Accept(IRulesVisitor a_visitor) {
		a_visitor.getRules("International", "Basic", "Player");

	}

}