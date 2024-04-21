package com.domain.musicplatform.repo;

import com.domain.musicplatform.entity.Artist;
import com.domain.musicplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(nativeQuery = true, value = "select * from User where password = :pass && email = :email")
    Optional<User> getUserByEmailAndPassword(@Param("pass") String pass, @Param("email") String email);
}
