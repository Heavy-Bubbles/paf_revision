package sg.edu.nus.iss.Workshop22.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.Workshop22.exception.ResourceNotFoundException;
import sg.edu.nus.iss.Workshop22.model.Rsvp;
import sg.edu.nus.iss.Workshop22.service.RsvpService;

@RestController
@RequestMapping(path= "/api", produces=MediaType.APPLICATION_JSON_VALUE)
public class RsvpController {
    
    @Autowired 
    RsvpService rsvpService;

    @GetMapping("/rsvps")
    public ResponseEntity<List<Rsvp>> getAllRsvp(){

        List<Rsvp> rsvps = rsvpService.findAllRsvp();

        if (rsvps.isEmpty()){
            throw new ResourceNotFoundException("No RSVPs found.");
        }
        return ResponseEntity.ok().body(rsvps);
        // return new ResponseEntity<List<Rsvp>>(rsvps, HttpStatus.OK)
    }

    @GetMapping("/rsvp")
    public ResponseEntity<List<Rsvp>> getRsvpsByName(@RequestParam(required = true) String q){

        String query = "%" + q +"%";

        List<Rsvp> rsvps = rsvpService.findRsvpByName(query);

        if (rsvps.isEmpty()){
            throw new ResourceNotFoundException("No RSVPs found.");
        }
        return ResponseEntity.ok().body(rsvps);
    }

    @PostMapping("/rsvp")
    public ResponseEntity<Boolean> saveRsvp(@RequestBody Rsvp rsvp){
        boolean saved = false;

        saved = rsvpService.saveRsvp(rsvp);

        if (saved){
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(saved, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/rsvps")
    public ResponseEntity<Boolean> batchSaveRsvp(@RequestBody List<Rsvp> rsvps){
        boolean saved = false;

        saved = rsvpService.batchSaveRsvp(rsvps);

        if (saved){
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(saved, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/rsvp/{rsvpId}")
    public ResponseEntity<Boolean> updateRsvp(@PathVariable("rsvpId") Integer id, @RequestBody Rsvp rsvp){
         
        Rsvp r = rsvpService.findById(id);
        boolean updated = false;

        if (r != null){
            updated = rsvpService.updateRsvp(rsvp, id);
        }

        if (updated){
            return new ResponseEntity<>(updated, HttpStatus.CREATED);
        }

        return ResponseEntity.ofNullable(updated);
    }

    @GetMapping("/rsvps/count")
    public ResponseEntity<Integer> countRsvps(){
        int count = rsvpService.countRsvp();

        return ResponseEntity.ok().body(count);
    }
}
