package com.habitHatch.security;

import com.habitHatch.db.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users,String> {

         Users findByUserId(String userId);

}
