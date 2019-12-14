package za.co.wethinkcode.model.instruments;

public class Bacon extends Instrument implements Product {
	private MarketTower _marketTower;

	public Bacon(String name, InstrumentDetails details) {
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
