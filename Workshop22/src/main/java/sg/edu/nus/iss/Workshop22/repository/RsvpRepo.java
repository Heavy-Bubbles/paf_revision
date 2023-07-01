package sg.edu.nus.iss.Workshop22.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.Workshop22.model.Rsvp;

@Repository
public class RsvpRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllSql = "select * from rsvp";
    private final String findByNameSql = "select * from rsvp where rsvp_name like '%?%'";
    private final String insertSql = "insert into rsvp(rsvp_name, email, phone, confirmation_date, comments) values (?, ?, ?, ?, ?)";
    private final String updateSql = "update rsvp set email = ? where id = ?";
    private final String countSql = "select count(*) from rsvp";

    public List<Rsvp> findAllRsvp(){
        List<Rsvp> rsvps = new ArrayList<Rsvp>();
        rsvps = jdbcTemplate.query(findAllSql, BeanPropertyRowMapper.newInstance(Rsvp.class));
        return rsvps;
        
    }

    public List<Rsvp> findRsvpsByName(String name){
        List<Rsvp> rsvps = new ArrayList<Rsvp>();
        rsvps = jdbcTemplate.query(findByNameSql, BeanPropertyRowMapper.newInstance(Rsvp.class), name);
        return rsvps;
    }

    public Boolean saveRsvp(Rsvp rsvp){
        int result = 0;
        result = jdbcTemplate.update(insertSql, rsvp.getName(), rsvp.getEmail(), rsvp.getPhone(),
            rsvp.getConfirmationDate(),rsvp.getComments());
        
        return result > 0 ? true : false;
    }

    public Boolean updateRsvp(Rsvp rsvp){
        int result = 0;
        result = jdbcTemplate.update(updateSql, rsvp.getEmail(), rsvp.getId());

        return result > 0 ? true : false;
    }

    public int countRsvp(){
        int result = jdbcTemplate.queryForObject(countSql, Integer.class);
        return result;
    }

    
    
}
