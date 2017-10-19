package BlackJack.model.rules;

public class AmericanBasicDealerRule implements IRulesFactory {
	
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

}
