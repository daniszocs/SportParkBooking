package org.sci.finalproject.SportParkBooking.repo;

import org.sci.finalproject.SportParkBooking.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    public User findByUserName(String userName);
    public User findByUserEmail(String userEmail);
    public User findByUserID(Long userID);
}
