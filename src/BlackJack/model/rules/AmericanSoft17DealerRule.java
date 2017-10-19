package BlackJack.model.rules;

public class AmericanSoft17DealerRule implements IRulesFactory {
	
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
	
}

