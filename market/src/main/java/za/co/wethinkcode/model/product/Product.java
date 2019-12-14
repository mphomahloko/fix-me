package za.co.wethinkcode.model.product;

import za.co.wethinkcode.model.market.MarketTower;

public interface Product {
	public void updateInstrumentDetails();
	public void registerMarket(MarketTower marketTower);
}
