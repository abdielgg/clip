package gg.abdiel.clip.simplerest;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/transaction")
	public Transaction transaction(@RequestParam(value = "name", defaultValue = "World") String name) {
		return (new Transaction(String.valueOf(counter.incrementAndGet()), String.format(template, name)));
	}
}