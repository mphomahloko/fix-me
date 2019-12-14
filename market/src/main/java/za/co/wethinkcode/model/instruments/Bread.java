package za.co.wethinkcode.model.instruments;

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
	}
}
