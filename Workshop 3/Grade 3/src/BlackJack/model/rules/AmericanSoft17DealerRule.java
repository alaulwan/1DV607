package BlackJack.model.rules;

class AmericanSoft17DealerRule implements IRulesFactory {

	@Override
	public INewGameStrategy GetNewGameRule() {
		return new AmericanNewGameStrategy();
	}

	@Override
	public IHitStrategy GetHitRule() {
		return new Soft17HitStrategy();
	}

	@Override
	public IWinStrategy GetWinRule() {
		return new DealerWinStrategy();
	}

	@Override
	public void Accept(IRulesVisitor a_visitor) {
		a_visitor.getRules("American", "Soft17", "Dealer");

	}

}
