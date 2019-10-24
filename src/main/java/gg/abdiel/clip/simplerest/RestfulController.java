package gg.abdiel.clip.simplerest;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gg.abdiel.clip.simplerest.entity.Transaction;
import gg.abdiel.clip.simplerest.entity.User;

@RestController
public class RestfulController {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
    EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);

    @RequestMapping(path = "/transaction", method = GET)
    public ResponseEntity<Transaction> get(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "transactionId") String transactionId)
                    throws CloneNotSupportedException {
        User u = new User();
        u.setId(userId);
        Transaction tr = new Transaction();
        tr.setId(transactionId);
        tr.setUser(u);
        
        EntityManager em = emf.createEntityManager();
        Transaction resTr = em.find(Transaction.class, transactionId);
        em.close();
        
        if (resTr == null) {
            return new ResponseEntity<Transaction>(tr, NOT_FOUND);
        }

        return new ResponseEntity<Transaction>(resTr, OK);
    }

    @RequestMapping("/hello")
    public ResponseEntity<Transaction> hello() {
        Transaction tr = new Transaction();
        tr.setId("uniqueId");
        tr.setAmount(3.1416);
        tr.setDescription("Hello Transaction");

        return new ResponseEntity<Transaction>(tr, OK);
    }

    @RequestMapping(path = "/transaction", method = POST)
    public ResponseEntity<Transaction> save(
            @RequestParam(value = "userId") String userId,
            @RequestBody Transaction transaction)
                    throws CloneNotSupportedException {
        User u = new User();
        u.setId(userId);
        Transaction tr = transaction.clone();
        tr.setUser(u);
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(tr);
        em.getTransaction().commit();
        em.close();

        return new ResponseEntity<Transaction>(tr, CREATED);
    }

    @RequestMapping(path = "/user", method = POST)
    public ResponseEntity<User> save(@RequestBody User user)
            throws CloneNotSupportedException {
        User u = user.clone();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();

        return new ResponseEntity<User>(u, CREATED);
    }
}