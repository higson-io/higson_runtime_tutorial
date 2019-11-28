package pl.decerto.hyperon.guide.context.adapter;

import pl.decerto.hyperon.ext.adapter.Adapter;
import pl.decerto.hyperon.ext.adapter.CollectionAdapter;
import pl.decerto.hyperon.ext.adapter.Mapping;
import pl.decerto.hyperon.guide.context.model.Quote;

/**
 * All custom adapters must extend {@link Adapter} and override method {@link #getMapping()}.
 */
public class QuoteAdapter extends Adapter {

	private Quote quote;

	public QuoteAdapter(Quote quote) {
		this.quote = quote;
	}

	/**
	 * This methods allows for path resolver to handle each subpath in proper way.
	 * @return mapping with supported subpaths
	 */
	@Override
	protected Mapping getMapping() {
		return new Mapping()
			.add("planCode", quote.getPlan()) 							// simple field
			.add("driver", new DriverAdapter(quote.getDriver())) 		    // adapter for driver
			.add("options", new CollectionAdapter<>(quote.getOptions(),   // collection adapter of option adapters
				OptionAdapter::new
			));
	}
}
