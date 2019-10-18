package gg.abdiel.clip.simplerest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface TransactionRepo extends JpaRepository<Transaction, String>{

}
