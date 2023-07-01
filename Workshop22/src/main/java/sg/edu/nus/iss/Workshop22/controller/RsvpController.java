package sg.edu.nus.iss.Workshop22.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.Workshop22.model.Rsvp;
import sg.edu.nus.iss.Workshop22.service.RsvpService;

@Controller
@RequestMapping("/api")
public class RsvpController {
    
    @Autowired 
    RsvpService rsvpService;

    @GetMapping("/rsvps")
    public ResponseEntity<List<Rsvp>> getAllRsvp(){
        
    }
}
