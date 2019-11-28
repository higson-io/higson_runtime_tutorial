package pl.decerto.hyperon.guide.context.adapter;

import pl.decerto.hyperon.ext.adapter.Adapter;
import pl.decerto.hyperon.ext.adapter.Mapping;
import pl.decerto.hyperon.guide.context.model.Option;

/**
 * All custom adapters must extend {@link Adapter} and override method {@link #getMapping()}.
 */
public class OptionAdapter extends Adapter {

	private final Option option;

	public OptionAdapter(Option option) {
		this.option = option;
	}

	/**
	 * This methods allows for path resolver to handle each subpath in proper way.
	 * @return mapping with supported subpaths
	 */
	@Override
	protected Mapping getMapping() {
		return new Mapping()
			.add("code", option.getCode())
			.add("quote", new QuoteAdapter(option.getQuote()));
	}
}
