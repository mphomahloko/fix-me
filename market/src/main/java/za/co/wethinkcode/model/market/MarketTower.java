package za.co.wethinkcode.model.market;

import za.co.wethinkcode.model.instruments.details.InstrumentDetails;
import za.co.wethinkcode.model.Decoder;

public class MarketTower extends Tower {

	// rejecting or accepting an order
	public InstrumentDetails processOrder() {
		Decoder decode = new Decoder(this._order);
		// go check all item names first
		InstrumentDetails dets = new InstrumentDetails(Integer.parseInt(decode.getPrice()), Integer.parseInt(decode.getQuantity()));
		dets.setName(decode.getProduct());
		return dets;
	}

	// return fixedmsg
	public void updatedProducts(String order) {
		this._order = order;
		_detailsChanged();
		return ;
	}
}
