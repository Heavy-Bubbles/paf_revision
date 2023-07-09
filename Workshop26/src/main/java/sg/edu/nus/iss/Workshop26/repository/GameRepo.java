package sg.edu.nus.iss.Workshop26.repository;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepo {
    @Autowired
    private MongoTemplate mongoTemplate;

    private final String C_GAMES = "games";
    private final String F_NAME = "name";
    private final String F_RANKING = "ranking";
    private final String IGNORE_CASE = "i";

    /*  
    db.games.find({ 
        name: { 
            $regex: "name",
            $options: "i"
        }
    }).skip(0)
    .limit(25) 
    */
    public List<Document> getGames(String name, int offset, int limit){
        Criteria criteria = Criteria.where(F_NAME)
            .regex(name, IGNORE_CASE);

        Query query = Query.query(criteria)
            .skip(offset)
            .limit(limit);

        return mongoTemplate.find(query, Document.class, C_GAMES);
    }

    /*
    db.games.find({ })
        .skip(0)
        .limit(25)
        .sort({ranking: 1})
    */
    public List<Document> getGamesByRank(int offset, int limit){
        Criteria criteria = new Criteria();

        Query query = Query.query(criteria)
            .with(
                Sort.by(Sort.Direction.ASC, F_RANKING)
            )
            .skip(offset)
            .limit(limit);

        return mongoTemplate.find(query, Document.class, C_GAMES);

    }

    /* 
    db.games.find({
        _id: ObjectId('id') 
    })
    */
    public Optional<Document> getGameById(String id){
        
        ObjectId docId = new ObjectId(id);

        return Optional.ofNullable(
            mongoTemplate.findById(docId, Document.class, C_GAMES)
        );

    }

}
