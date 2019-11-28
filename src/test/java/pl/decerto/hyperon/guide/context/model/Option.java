package pl.decerto.hyperon.guide.context.model;

public class Option {

	private final String code;

	private Quote quote;

	public Option(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public Quote getQuote() {
		return quote;
	}
}
