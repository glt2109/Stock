package sto;

public class Quote {
	
	private final String symbol;
	private final Double price;
	private final String companyName;
	private final Boolean isValid;
	
	public Quote(String symbol, Double price, String companyName, Boolean isValid) {
		this.symbol = symbol;
		this.price = price;
		this.companyName = companyName;
		this.isValid = isValid;
	}

	public String getSymbol() {
		return symbol;
	}

	public Double getPrice() {
		return price;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Boolean getIsValid() {
		return isValid;
	}
}
