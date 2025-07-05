package com.habitHatch.security.entity;

import com.habitHatch.db.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users,String> {

         Users findByUserId(String userId);

}
