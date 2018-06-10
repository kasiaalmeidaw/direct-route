package goeuro.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class DirectController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	DirectConnectionService directConnectionService;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@GetMapping("/direct")
	public ResponseEntity<DirectRoute> addTransaction(@RequestParam("dep_sid") String dep_sid,
			@RequestParam("arr_sid") String arr_sid) {

		Boolean isDirect = directConnectionService.existDirectConnection(Integer.valueOf(dep_sid).intValue(),
				Integer.valueOf(arr_sid).intValue());
		DirectRoute directRoute = new DirectRoute();
		directRoute.setDep_sid(Integer.valueOf(dep_sid));
		directRoute.setArr_sid(Integer.valueOf(arr_sid));
		directRoute.setDirect_bus_route(isDirect);
		return ResponseEntity.status(HttpStatus.OK).body(directRoute);

	}
}
