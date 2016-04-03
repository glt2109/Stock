package application;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import service.Service;
import service.YahooFinance;
import sto.Query;
import sto.Quote;

@Configuration
@ComponentScan
@SpringBootApplication
public class Application {
	
	/*
	@Bean
	Service mockService() {
		return new Service() {
			public Quote tryQuery(Query stockQuery) {
				return new Quote("test", "10000.00", "test, inc.");
			}
		};
	} */
	
	@Bean
	Service yahooService() {
		return new YahooFinance() {

		};
	}
	
	public static void main(String[] args) throws Exception {
		/*
		ApplicationContext context = 
		          new AnnotationConfigApplicationContext(Application.class);
		Service testService = context.getBean(Service.class);
		testService.tryQuery(new Query("fit"));
		*/
		
		SpringApplication.run(Application.class, args);
		
		Scanner keyboard = new Scanner(System.in);
		String input;
		Service service = new YahooFinance();
		
		while(keyboard.hasNext())
		{
			input = keyboard.next();
			
			Query stockQuery = new Query(input);
			Quote quote = service.tryQuery(stockQuery);

	        System.out.println(quote.getSymbol());	
			System.out.println(quote.getCompanyName());
	        System.out.println(quote.getPrice());
	        
		}
			
		keyboard.close();
	}

}
