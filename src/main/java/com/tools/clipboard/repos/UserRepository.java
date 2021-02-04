package com.tools.clipboard.repos;

import com.tools.clipboard.models.Message;
import com.tools.clipboard.models.Room;
import com.tools.clipboard.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}