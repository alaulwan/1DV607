package BlackJack.model.rules;

public class RulesFactory {

  public IHitStrategy GetHitRule() {
	return new Soft17HitStrategy();
    //return new BasicHitStrategy();
  }
  
  public IWinStrategy GetWinRule(){
		return new PlayerWinStrategy();
		//return new DealerWinStrategy();
	}

  public INewGameStrategy GetNewGameRule() {
    return new AmericanNewGameStrategy();
  }
}