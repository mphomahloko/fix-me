package za.co.wethinkcode.model.market;

import za.co.wethinkcode.model.product.Product;

import java.util.ArrayList;

public abstract class Tower {
	private ArrayList<Product> _brokersObserving = new ArrayList<Product>();
	protected String _order;

	public void register(Product product) {
		this._brokersObserving.add(product);
		return ;
	}

	public void unregister(Product product) {
		this._brokersObserving.remove(product);
	}

	protected void _detailsChanged() {
		for (int i = 0; i < _brokersObserving.size(); i += 1)
			_brokersObserving.get(i).updateInstrumentDetails();
		return ;
	}
}
