package sg.edu.nus.iss.Workshop22.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.Workshop22.model.Rsvp;
import sg.edu.nus.iss.Workshop22.repository.RsvpRepo;

@Service
public class RsvpService {

    @Autowired
    RsvpRepo rsvpRepo;

    public List<Rsvp> findAllRsvp(){
        return rsvpRepo.findAllRsvp();
    }

    public List<Rsvp> findRsvpByName(String name){
        return rsvpRepo.findRsvpsByName(name);
    }

    public Boolean saveRsvp(Rsvp rsvp){
        return rsvpRepo.saveRsvp(rsvp);
    }

    public Boolean updateRsvp(Rsvp rsvp){
        return rsvpRepo.updateRsvp(rsvp);
    }

    public int countRsvp(){
        return rsvpRepo.countRsvp();
    }

}
