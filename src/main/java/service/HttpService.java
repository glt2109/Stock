package service;

import sto.Query;
import sto.Quote;

public interface HttpService {
	
	Quote tryQuery(Query stockQuery);

}
