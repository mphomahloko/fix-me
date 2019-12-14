package za.co.wethinkcode.model.instruments;

import za.co.wethinkcode.model.instruments.details.InstrumentDetails;
import za.co.wethinkcode.model.market.MarketTower;
import za.co.wethinkcode.model.product.Product;

public class Bread extends Instrument implements Product {
	private MarketTower _marketTower;

	public Bread(String name, InstrumentDetails details) {
		super(name, details);
		return ;
	}

	public void updateInstrumentDetails() {
	}

	public void registerMarket(MarketTower marketTower) {
		this._marketTower = marketTower;
		this._marketTower.register(this);
		// update broadcast to brokers
		return ;
	}
}
