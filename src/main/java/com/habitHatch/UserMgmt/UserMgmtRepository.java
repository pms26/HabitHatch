package com.habitHatch.UserMgmt;
import com.habitHatch.UserMgmt.entity.UserRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMgmtRepository extends CrudRepository<UserRequest, String> {
    // Define any additional query methods if needed

}
