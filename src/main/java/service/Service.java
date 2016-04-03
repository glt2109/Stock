package service;

import sto.Query;
import sto.Quote;

public interface Service {
	
	Quote tryQuery(Query stockQuery);

}
