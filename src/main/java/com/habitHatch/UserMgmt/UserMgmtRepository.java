package com.habitHatch.UserMgmt;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMgmtRepository extends CRUDRepository<User, String> {
    // Define any additional query methods if needed
}
