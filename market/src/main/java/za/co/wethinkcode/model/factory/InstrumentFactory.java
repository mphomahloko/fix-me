package za.co.wethinkcode.model.factory;

// Product factory
public abstract class InstrumentFactory {
	public static Instrument newInstrument(String type, String name, int price, int qty) {
		if (type.equals("Bread")) return new Bread(name, new InstrumentDetails(price, qty));
		if (type.equals("Bacon")) return new Bread(name, new InstrumentDetails(price, qty));
		return new Bread(name, new InstrumentDetails(price, qty));
	}
}
