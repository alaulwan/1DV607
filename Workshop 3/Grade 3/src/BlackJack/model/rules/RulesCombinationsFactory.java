package BlackJack.model.rules;

public class RulesCombinationsFactory implements IRulesCombinationsFactory {

	@Override
	public IRulesFactory AmericanBasicDealerRules() {
		return new AmericanBasicDealerRule();
	}

	@Override
	public IRulesFactory InternationalBasicPlayes() {
		return new InternationalBasicPlayerRule();
	}

	@Override
	public IRulesFactory AmericanSoft17DealerRules() {
		return new AmericanSoft17DealerRule();
	}

}
