package gg.abdiel.clip.simplerest;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import javax.persistence.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gg.abdiel.clip.simplerest.entity.*;

@RestController
public class RestfulController {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
    EntityManagerFactory factory = context.getBean(EntityManagerFactory.class);

    @RequestMapping(path = "/transaction/random", method = GET)
    public ResponseEntity<Transaction> get() {
        Transaction tr = new Transaction();

        EntityManager manager = factory.createEntityManager();

        TypedQuery<Transaction> query = manager
                .createQuery("select tr from Transaction tr order by rand()", Transaction.class)
                .setMaxResults(1);

        tr = query.getSingleResult();

        manager.close();

        return new ResponseEntity<Transaction>(tr, OK);
    }

    @RequestMapping(path = "/transactions", method = GET)
    public ResponseEntity<List<Transaction>> get(
            @RequestParam(value = "userId") String userId) {

        EntityManager manager = factory.createEntityManager();

        TypedQuery<Transaction> query = manager
                .createQuery("select tr from Transaction tr join tr.user usr where usr.id = :userId", Transaction.class)
                .setParameter("userId", userId);
        List<Transaction> resultList = query.getResultList();
        
        manager.close();

        return new ResponseEntity<List<Transaction>>(resultList, OK);
    }
    
    @RequestMapping(path = "/transaction", method = GET)
    public ResponseEntity<Transaction> get(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "transactionId") String transactionId) {

        User u = new User();
        u.setId(userId);
        Transaction tr = new Transaction();
        tr.setId(transactionId);
        tr.setUser(u);

        EntityManager manager = factory.createEntityManager();
        Transaction resTr = manager.find(Transaction.class, transactionId);
        manager.close();

        if (resTr == null || !resTr.getUser().getId().equals(userId)) {
            resTr = new Transaction();
            resTr.setDescription("Entity Not Found");
            return new ResponseEntity<Transaction>(resTr, NOT_FOUND);
        }
        
        manager.close();

        return new ResponseEntity<Transaction>(resTr, OK);
    }

    @RequestMapping("/transaction/hello")
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

        User user = new User();
        user.setId(userId);
        Transaction tr = transaction.clone();
        tr.setUser(user);
        
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(tr);
        manager.getTransaction().commit();
        manager.close();

        return new ResponseEntity<Transaction>(tr, CREATED);
    }

    @RequestMapping(path = "/user", method = POST)
    public ResponseEntity<User> save(
            @RequestBody User user)
                    throws CloneNotSupportedException {

        User u = user.clone();

        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(u);
        manager.getTransaction().commit();
        manager.close();

        return new ResponseEntity<User>(u, CREATED);
    }
}