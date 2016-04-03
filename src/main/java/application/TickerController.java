package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.Service;
import sto.Query;
import sto.Quote;

@RestController
@Component
public class TickerController {

	final private Service service;

	@Autowired
	public TickerController(Service service) {
		this.service = service;
	}
	
	@RequestMapping("/ticker")
	public Quote getQuote(@RequestParam(value="q", defaultValue="") String searchCriteria) {
		
		Query query = new Query(searchCriteria);
		return service.tryQuery(query);
	}

}
