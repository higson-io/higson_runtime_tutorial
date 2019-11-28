package pl.decerto.hyperon.guide.context.adapter;

import java.util.Date;

import pl.decerto.hyperon.ext.adapter.Adapter;
import pl.decerto.hyperon.ext.adapter.Mapping;
import pl.decerto.hyperon.guide.context.model.Driver;
import pl.decerto.hyperon.runtime.rhino.RhinoDate;

/**
 * All custom adapters must extend {@link Adapter} and override method {@link #getMapping()}.
 */
public class DriverAdapter extends Adapter {

	private final Driver driver;

	public DriverAdapter(Driver driver) {
		this.driver = driver;
	}

	/**
	 * This methods allows for path resolver to handle each subpath in proper way.
	 * @return mapping with supported subpaths
	 */
	@Override
	protected Mapping getMapping() {
		return new Mapping()
			.add("firstname", driver.getFirstName())
			.add("lastname", driver.getLastName())
			.add("dateofbirth", driver.getDateOfBirth())
			.add("gender", driver.getGender())
			.add("age", computeAge())
			.add("numberOfAccidents", driver.getNumberOfAccidents())
			.add("numberOfTickets", driver.getNumberOfTickets());
	}

	private int computeAge() {
		return RhinoDate.getAbsoluteYearDiff(new Date(), driver.getDateOfBirth());
	}
}
