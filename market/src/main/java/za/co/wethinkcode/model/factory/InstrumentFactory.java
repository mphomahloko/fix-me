package za.co.wethinkcode.model.factory;


import za.co.wethinkcode.model.instruments.Bread;
import za.co.wethinkcode.model.instruments.Bacon;
import za.co.wethinkcode.model.instruments.Egg;

// Product factory
public abstract class InstrumentFactory {
	public static Instrument newInstrument(String type, String name, int price, int qty) {
		if (type.equals("Bread")) return new Bread(name, new InstrumentDetails(price, qty));
		if (type.equals("Bacon")) return new Bacon(name, new InstrumentDetails(price, qty));
		return new Egg(name, new InstrumentDetails(price, qty));
	}
}
