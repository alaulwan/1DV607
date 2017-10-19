package BlackJack.model.rules;

public class InternationalBasicPlayerRule implements IRulesFactory {
	
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
	
}