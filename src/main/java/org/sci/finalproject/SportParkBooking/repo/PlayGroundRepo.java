package org.sci.finalproject.SportParkBooking.repo;

import org.sci.finalproject.SportParkBooking.model.PlayGround;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayGroundRepo extends CrudRepository<PlayGround,Long> {

    public PlayGround findByPlayGroundName(String playGroundName);
    public PlayGround findByPlayGroundID(Long playGroundID);

}
