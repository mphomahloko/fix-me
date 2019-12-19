package za.co.wethinkcode.model.market;

import za.co.wethinkcode.model.executor.MarketExecutor;
import za.co.wethinkcode.model.instruments.details.InstrumentDetails;

public class MarketTower extends Tower {

	// rejecting or accepting an order
	public InstrumentDetails processOrder() {
		MarketExecutor _market = MarketExecutor.getMarket();
		// String[] instrumentDetails = null;
		// while (_market._in.hasNextLine()){

			System.out.println(this._order);
		// }
		InstrumentDetails dets;
//		if (instrumentDetails[0].equals("Egg")) {
			dets = new InstrumentDetails(2, 2);
		//}
		return dets; 
	}

	public void updatedProducts(String order) {
		this._order = order;
		_detailsChanged();
		return ;
	}
}
