package application.tests;

import org.junit.Test;

import service.Service;
import service.YahooFinance;
import sto.Query;
import sto.Quote;

import static org.junit.Assert.*;

public class TestYahooFinance {
	
	@Test
	public void returnsQuoteResponseForValidQuery() throws Exception {
		Query query = new Query("fit");
		Service service = new YahooFinance();
		Quote quote = service.tryQuery(query);

		assertEquals("fit", quote.getSymbol());
		assertTrue(quote.getPrice() > 0.0);
		assertEquals("Fitbit, Inc. Class A Common Sto", quote.getCompanyName());
		assertTrue(quote.getIsValid());
	}	
	
	@Test
	public void returnsResponseForInvalidQuery() {
		Query query = new Query("");
		Service service = new YahooFinance();
		Quote quote = service.tryQuery(query);
		
		assertTrue(quote.getSymbol().equals("N/A"));
		assertTrue(quote.getPrice() == 0.00);
		assertTrue(quote.getCompanyName().equals("N/A"));
		assertFalse(quote.getIsValid());
	}
	
}
