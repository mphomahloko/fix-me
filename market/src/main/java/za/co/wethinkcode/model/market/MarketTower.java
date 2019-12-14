package za.co.wethinkcode.model.market;

import za.co.wethinkcode.model.executor.MarketExecutor;
import za.co.wethinkcode.model.instruments.details.InstrumentDetails;

public class MarketTower extends Tower {

	// rejecting or accepting an order
	public InstrumentDetails processOrder() {
		MarketExecutor _market = MarketExecutor.getMarket();
		String[] instrumentDetails = null;
		instrumentDetails = _market.Order.split(" ");
		InstrumentDetails dets;
//		if (instrumentDetails[0].equals("Egg")) {
			dets = new InstrumentDetails(Integer.valueOf(instrumentDetails[1]),Integer.valueOf(instrumentDetails[2]));
		//}
		return dets; 
	}

	public void updatedProducts() {
		_detailsChanged();
		return ;
	}
}
