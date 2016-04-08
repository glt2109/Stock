package application;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import service.HttpService;
import service.YahooFinance;
import sto.Query;
import sto.Quote;

@Configuration
@ComponentScan
@SpringBootApplication
public class Application {
	
	
	@Bean
	HttpService mockService() {
		return new HttpService() {
			public Quote tryQuery(Query stockQuery) {
				return new Quote("TEST", 100.00, "Test, inc.", true);
			}
		};
	} 
	
	/*
	@Bean
	HttpService yahooService() {
		return new YahooFinance() {

		};
	} */
	
	public static void main(String[] args) throws Exception {
		
		ApplicationContext context = 
		          new AnnotationConfigApplicationContext(Application.class);
		HttpService testService = context.getBean(HttpService.class);
		testService.tryQuery(new Query("fit"));
		
		
		SpringApplication.run(Application.class, args);
		
		
		Scanner keyboard = new Scanner(System.in);
		String input;
		//HttpService service = new YahooFinance();
		
		while(keyboard.hasNext())
		{
			input = keyboard.next();
			
			Query stockQuery = new Query(input);
			Quote quote = testService.tryQuery(stockQuery);

	        System.out.println(quote.getSymbol());	
			System.out.println(quote.getCompanyName());
	        System.out.println(quote.getPrice());
	        
		}
			
		keyboard.close();
	}

}
