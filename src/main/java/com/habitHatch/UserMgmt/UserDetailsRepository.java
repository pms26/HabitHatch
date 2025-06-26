package com.habitHatch.UserMgmt;

import com.habitHatch.db.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users,String> {

    @Query("Select u from Users u where u.userId= :userId")
    public Users getUserByUserName(@Param("userId") String userId);
}
