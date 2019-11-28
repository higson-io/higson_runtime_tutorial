package pl.decerto.hyperon.guide.context;

import org.apache.commons.lang3.StringUtils;

import pl.decerto.hyperon.ext.adapter.Adapter;
import pl.decerto.hyperon.guide.context.adapter.DriverAdapter;
import pl.decerto.hyperon.guide.context.adapter.QuoteAdapter;
import pl.decerto.hyperon.guide.context.model.Driver;
import pl.decerto.hyperon.guide.context.model.Quote;
import pl.decerto.hyperon.runtime.core.HyperonContext;

public class MotorContext extends HyperonContext {

	private Quote quote;
	private Driver driver;

	public MotorContext(Quote quote) {
		this.quote = quote;
	}

	public Quote getQuote() {
		return quote;
	}

	public Driver getDriver() {
		if (driver == null && getQuote() != null) {
			driver = getQuote().getDriver();
		}
		return driver;
	}

	/**
	 * get value from path,
	 * where path is valid path in canonical model,
	 * for example:
	 * - quote.insured.professionCode
	 * - quote.insured.age
	 * - quote.productCode
	 * - cover.code
	 */
	@Override
	public Object get(String path) {
		String name = getFirstToken(path);
		String subpath = skipFirstToken(path);

		Adapter adapter = null;
		if (name.equals("quote")) {
			adapter = new QuoteAdapter(getQuote());
		}

		if (name.equals("driver")) {
			adapter = new DriverAdapter(getDriver());
		}

		if (adapter != null) {
			return adapter.get(subpath);
		}

		return null;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	private String getFirstToken(String path) {
		return StringUtils.substringBefore(path, ".");
	}

	private String skipFirstToken(String path) {
		return StringUtils.substringAfter(path, ".");
	}
}
