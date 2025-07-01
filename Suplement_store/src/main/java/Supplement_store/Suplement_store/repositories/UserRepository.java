package Supplement_store.Suplement_store.repositories;

import Supplement_store.Suplement_store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
