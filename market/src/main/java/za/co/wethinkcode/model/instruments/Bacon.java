package za.co.wethinkcode.model.instruments;

import za.co.wethinkcode.core.App;
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
		InstrumentDetails dets = _marketTower.processOrder();
		if (dets.getPrice() >= this._details.getPrice()){
			this._details = new InstrumentDetails(this._details.getPrice(), this._details.getQty() - dets.getQty());
		}
		App.logMessage.add("Bacon#"+_name+"("+_id+") works.");
	}

	@Override
	public void registerMarket(MarketTower marketTower) {
		this._marketTower = marketTower;
		this._marketTower.register(this);
		// update broadcast to brokers
		App.logMessage.add("Tower says: Bacon#"+_name+"("+_id+") registered to tower.");
		return ;
	}

}
