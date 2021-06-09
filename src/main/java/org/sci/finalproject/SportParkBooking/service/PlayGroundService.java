package org.sci.finalproject.SportParkBooking.service;

import org.sci.finalproject.SportParkBooking.model.PlayGround;
import org.sci.finalproject.SportParkBooking.repo.PlayGroundRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayGroundService {

    @Autowired
    private PlayGroundRepo playGroundRepo;

    public boolean register(PlayGround playGround) {
        PlayGround foundPlayGround = playGroundRepo.findByPlayGroundName(playGround.getPlayGroundName());
        if (foundPlayGround==null){
            playGroundRepo.save(playGround);
            return true;
        }
        return false;
    }

    public Iterable<PlayGround> findAll() {
        return playGroundRepo.findAll();
    }

    public Long returnPlayGroundID(String playGroundName){
        return playGroundRepo.findByPlayGroundName(playGroundName).getPlayGroundID();
    }
    public String returnPlayGroundName(Long playGroundID){
        return playGroundRepo.findByPlayGroundID(playGroundID).getPlayGroundName();
    }

    public int returnPricePerHour(String playGroundName){
        return playGroundRepo.findByPlayGroundName(playGroundName).getPricePerHour();
    }

    public boolean updatePlayGround(PlayGround oldPlayGround, PlayGround newPlayGround) {
        PlayGround foundPlayGround = playGroundRepo.findByPlayGroundName(oldPlayGround.getPlayGroundName());

        if (newPlayGround.getPlayGroundName().equals(foundPlayGround.getPlayGroundName())) {
            playGroundRepo.delete(oldPlayGround);
            playGroundRepo.save(oldPlayGround);
            return true;
        }
        return false;
    }

    public void deletePlayGound(PlayGround playGroundToDelete){
        playGroundRepo.delete(playGroundToDelete);
    }

}
