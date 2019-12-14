package za.co.wethinkcode.model.instruments;

import za.co.wethinkcode.model.market.MarketTower;
import za.co.wethinkcode.model.product.Product;
import za.co.wethinkcode.model.instruments.details.InstrumentDetails;

public class Bacon extends Instrument implements Product {
	private MarketTower _marketTower;

	public Bacon(String name, InstrumentDetails details) {
		super(name, details);
		return;
	}

	public void updateInstrumentDetails() {

	}

	@Override
	public void registerMarket(MarketTower marketTower) {
		this._marketTower = marketTower;
		this._marketTower.register(this);
		// update broadcast to brokers
		return ;
	}

}
