package sg.edu.nus.iss.Workshop22.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final String findByNameSql = "select * from rsvp where rsvp_name like ?";
    private final String insertSql = "insert into rsvp(rsvp_name, email, phone, confirmation_date, comments) values (?, ?, ?, ?, ?)";
    private final String updateSql = "update rsvp set email = ? where id = ?";
    private final String countSql = "select count(*) from rsvp";
    private final String findByIdSQL = "select * from rsvp where id = ?";

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
            rsvp.getConfirmationDate(), rsvp.getComments());
        
        return result > 0 ? true : false;
    }

    public Boolean updateRsvp(Rsvp rsvp, Integer id){
        int result = 0;
        result = jdbcTemplate.update(updateSql, rsvp.getEmail(), id);

        return result > 0 ? true : false;
    }

    public int countRsvp(){
        int result = jdbcTemplate.queryForObject(countSql, Integer.class);
        return result;
    }

    public int[] batchSaveRsvp(final List<Rsvp> rsvps){
        List<Object[]> params = rsvps.stream()
            .map(rsvp -> new Object[]{rsvp.getName(), rsvp.getEmail(), rsvp.getPhone(),
            rsvp.getConfirmationDate(), rsvp.getComments()})
            .collect(Collectors.toList());
            
        int added[] = jdbcTemplate.batchUpdate(insertSql, params);
        return added;
    }

    public Rsvp findById(int id){
        return jdbcTemplate.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(Rsvp.class), id);
    }
    
}
