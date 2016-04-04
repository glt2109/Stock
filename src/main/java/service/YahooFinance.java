package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import sto.Query;
import sto.Quote;

/**
 * 
 * Change from CSV to a better data structure.
 * 
 * @author gentrytran
 *
 */
public class YahooFinance implements Service {
	
	private static String baseUrl = "http://finance.yahoo.com/d/quotes.csv?s=";
	
	@Override
	public Quote tryQuery(Query stockQuery) {
		
		Quote quote = null;
		
		if (stockQuery.getQuery() == null || stockQuery.getQuery().isEmpty()) {
			return new Quote("N/A", 0.00, "N/A", false);
		}
		
		try {
			URL companyUrl = createBaseUrl(stockQuery);
			URL nameUrl = createUrlForCompanyNameRequest(stockQuery);
			String csv = makeUrlRequest(companyUrl);
			String companyName = makeUrlRequest(nameUrl);
			quote = parseCsv(csv, companyName);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return quote;
	}
	
	private static URL createBaseUrl(Query query) throws MalformedURLException {
	
		String symbolAndPriceFlags = "&f=sl1";
		URL url = new URL(baseUrl + query.getQuery() + symbolAndPriceFlags);
		
		return url;
	}

	private static URL createUrlForCompanyNameRequest(Query query) throws MalformedURLException {
		
		String nameFlag = "&f=n";
		URL url = new URL(baseUrl + query.getQuery() + nameFlag);
		
		return url;
	}
	
	private static String makeUrlRequest(URL companyUrl) throws Exception{
		
		URLConnection yc = companyUrl.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        
        StringBuilder builder = new StringBuilder();
        String symbol = "";

        while ((symbol = in.readLine()) != null) {
            builder.append(symbol);
        }
       
        return builder.toString();
	}	
	
	private static Quote parseCsv(String csv, String companyName) {
		String[] values = csv.split(",");
		
		if(!isQuoteIsValid(values)) {
			return new Quote("N/A", 0.00, "N/A", false);
		}

		Quote quote = null;
		
		try {
			quote = new Quote(removeRedundantQuotes(values[0]), Double.parseDouble(values[1]), removeRedundantQuotes(companyName), true);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("No return value.");
		}
				
		return quote;
	}
	
	private static Boolean isQuoteIsValid(String[] values) {
		if(Arrays.asList(values).contains("N/A")) {
			return false;
		}
		return true;
	}
	
	private static String removeRedundantQuotes(String value) {
		return value.replaceAll("^\"|\"$", "");
	}

}
