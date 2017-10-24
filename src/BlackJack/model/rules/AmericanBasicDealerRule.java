package BlackJack.model.rules;

class AmericanBasicDealerRule implements IRulesFactory {

	@Override
	public INewGameStrategy GetNewGameRule() {
		return new AmericanNewGameStrategy();
	}

	@Override
	public IHitStrategy GetHitRule() {
		return new BasicHitStrategy();
	}

	@Override
	public IWinStrategy GetWinRule() {
		return new DealerWinStrategy();
	}

	@Override
	public void Accept(IRulesVisitor a_visitor) {
		a_visitor.getRules("American", "Basic", "Dealer");

	}

}
