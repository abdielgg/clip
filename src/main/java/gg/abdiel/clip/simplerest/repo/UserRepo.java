package gg.abdiel.clip.simplerest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import gg.abdiel.clip.simplerest.entity.User;

@Transactional
public interface UserRepo extends JpaRepository<User, String> {

}
