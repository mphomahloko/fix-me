package za.co.wethinkcode.model.instruments;

import za.co.wethinkcode.model.instruments.details.InstrumentDetails;
import za.co.wethinkcode.model.market.MarketTower;
import za.co.wethinkcode.model.product.Product;

public class Egg extends Instrument implements Product {
	private MarketTower _marketTower;

	public Egg(String name, InstrumentDetails details) {
		super(name, details);
		return ;
	}

	public void updateInstrumentDetails() {
		InstrumentDetails dets = _marketTower.processOrder();
		if (dets.getPrice() >= this._details.getPrice()) {
			this._details = new InstrumentDetails(this._details.getPrice(), this._details.getQty() - dets.getQty());
		}
	}

	@Override
	public void registerMarket(MarketTower marketTower) {
		this._marketTower = marketTower;
		this._marketTower.register(this);
		return ;
	}

	@Override
	public String toString() {
		return this._name + " price: R" + _details.getPrice() + " available items: " + this._details.getQty();
	}
}
