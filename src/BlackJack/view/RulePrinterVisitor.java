package BlackJack.view;

import BlackJack.model.rules.*;

public class RulePrinterVisitor implements IRulesVisitor {

	@Override
	public void PrintRules(IRulesFactory rules) {
		if (rules instanceof AmericanBasicDealerRule) {
			System.out.println("Game Strategy: American");
			System.out.println("Hit Strategy: Basic");
			System.out.println("Win Strategy: Dealer");
			System.out.println("");
		}
		else if (rules instanceof AmericanSoft17DealerRule) {
				System.out.println("Game Strategy: American");
				System.out.println("Hit Strategy: Soft-17");
				System.out.println("Win Strategy: Dealer");
				System.out.println("");
			
		}
		else if (rules instanceof InternationalBasicPlayerRule) {
			System.out.println("Game Strategy: International");
			System.out.println("Hit Strategy: Soft-17");
			System.out.println("Win Strategy: Player");
			System.out.println("");
		
	}
		
	}
}