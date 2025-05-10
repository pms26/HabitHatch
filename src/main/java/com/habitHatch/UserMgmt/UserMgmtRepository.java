package com.habitHatch.UserMgmt;
import com.habitHatch.UserMgmt.entity.UserRequest;
import com.habitHatch.UserMgmt.entity.UserResponse;
import com.habitHatch.db.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMgmtRepository extends JpaRepository<Users, String> {
    // Define any additional query methods if needed


}
