package com.domain.musicplatform.repo;

import com.domain.musicplatform.entity.Artist;
import com.domain.musicplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}
