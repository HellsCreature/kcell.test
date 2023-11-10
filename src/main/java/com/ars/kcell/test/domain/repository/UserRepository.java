package com.ars.kcell.test.domain.repository;

import com.ars.kcell.test.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

  Optional<User> findByName(String name);
}
