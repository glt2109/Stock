package service;

import sto.Query;
import sto.Quote;

public class MockService implements HttpService {

	@Override
	public Quote tryQuery(Query stockQuery) {
		return new Quote("TEST", 100.00, "Test, Inc.", true);
	}

}
