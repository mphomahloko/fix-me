package za.co.wethinkcode.model.instruments;

public abstract class Instrument {
	protected long _id;
	protected String _name;
	protected InstrumentDetails _details;
	private static long _idGenerated = 0;

	protected Instrument(String name, InstrumentDetails details) {
		this._name = name;
		this._details = details;
		this._id = nextId();
		return ;
	}

	protected long _nextId() {
		return this._idGenerated += 1;
	}
}
