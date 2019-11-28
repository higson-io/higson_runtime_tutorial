package pl.decerto.hyperon.guide.context.model;

import java.util.ArrayList;
import java.util.List;

public class Quote {

	private final String plan;
	private Driver driver;

	private List<Option> options = new ArrayList<>();

	public Quote(String plan) {
		this.plan = plan;
	}

	public Quote(String plan, Driver driver) {
		this.plan = plan;
		this.driver = driver;
	}

	public String getPlan() {
		return plan;
	}

	public Driver getDriver() {
		return driver;
	}

	public void addOption(Option option) {
		option.setQuote(this);
		options.add(option);
	}

	public List<Option> getOptions() {
		return options;
	}
}
