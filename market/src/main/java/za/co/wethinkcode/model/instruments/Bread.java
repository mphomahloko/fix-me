package za.co.wethinkcode.model.instruments;

import za.co.wethinkcode.core.App;
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
		InstrumentDetails dets = _marketTower.processOrder();
		if (dets.getPrice() >= this._details.getPrice()) {
			this._details= new InstrumentDetails(this._details.getPrice(),this._details.getQty() - dets.getQty());
		}
		App.logMessage.add("Bread#"+_name+"("+_id+") works.");
	}

	public void registerMarket(MarketTower marketTower) {
		this._marketTower = marketTower;
		this._marketTower.register(this);
		// update broadcast to brokers
		App.logMessage.add("Tower says: Bread#"+_name+"("+_id+") registered to tower.");
		return ;
	}
}
