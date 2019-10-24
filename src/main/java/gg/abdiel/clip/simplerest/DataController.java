package gg.abdiel.clip.simplerest;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
    EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);

    @RequestMapping("/hello")
    public Transaction hello(
            @RequestParam(value = "name", defaultValue = "World") String name) {
        return (new Transaction(String.valueOf(counter.incrementAndGet()), String.format(template, name)));
    }

    @RequestMapping(path = "/transaction/new", method = POST)
    public ResponseEntity<Transaction> update(
            @RequestParam(value = "userId") String userId,
            @RequestBody Transaction transaction)
                    throws CloneNotSupportedException {
        User u = new User();
        u.setId(userId);
        Transaction tr = transaction.clone();
        tr.setUser(u);

        return new ResponseEntity<Transaction>(tr, OK);
    }

    @RequestMapping(path = "/user/new", method = POST)
    public ResponseEntity<User> update(
            @RequestBody User user)
                    throws CloneNotSupportedException {
        User u = user.clone();
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
        
        return new ResponseEntity<User>(u, OK);
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