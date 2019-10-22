package gg.abdiel.clip.simplerest;

import static org.springframework.http.HttpStatus.OK;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gg.abdiel.clip.simplerest.entity.Transaction;
import gg.abdiel.clip.simplerest.entity.User;

@RestController
public class DataController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/hello")
    public Transaction hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return (new Transaction(String.valueOf(counter.incrementAndGet()), String.format(template, name)));

    }

    @RequestMapping("/transaction/new")
    public ResponseEntity<Transaction> update(@RequestParam(value = "userId") String userId,
            @RequestBody Transaction transaction) throws CloneNotSupportedException {
        User u = new User();
        u.setId(userId);
        Transaction tr = transaction.clone();
        tr.setUser(u);

        return new ResponseEntity<Transaction>(tr, OK);
    }

    @RequestMapping("/transaction")
    public ResponseEntity<Transaction> get() {
        Transaction tr = new Transaction();
        tr.setId("idUni");
        tr.setAmount(3.1416);
        tr.setDescription("Hello Transaction");

        return new ResponseEntity<Transaction>(tr, OK);
    }
}