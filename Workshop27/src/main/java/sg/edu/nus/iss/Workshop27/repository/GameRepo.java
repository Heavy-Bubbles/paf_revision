package sg.edu.nus.iss.Workshop27.repository;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepo {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    /*
    db.games.find({
        name: {
            $regex: 'name',
            $options: "i"
        }},
        { _id: 0, name: 1, gid: 1 })
    .skip(0)
    .limit(25)
     */
    public List<Document> getGamesByName(String name, int limit, int offset){
        
        // filter
        Criteria criteria = Criteria.where("name")
            .regex(name, "i");

        // limit and offset
        Query query = Query.query(criteria)
            .skip(offset)
            .limit(limit);

        // projection
        query.fields()
            .exclude("_id")
            .include("name", "gid");

        return mongoTemplate.find(query, Document.class, "games");
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
                Sort.by(Sort.Direction.ASC, "ranking")
            )
            .skip(offset)
            .limit(limit);

        return mongoTemplate.find(query, Document.class, "games");
    }

    /*
     * db.games.find({ gid: gid })
     */
    public Optional<Document> getGameById(Integer id){
        
        Criteria criteria = Criteria.where("gid")
            .is(id);

        Query query = Query.query(criteria);

        List<Document> result = mongoTemplate.find(query, Document.class, "games");

        if (result.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(result.get(0));
    }

    /*
     db.games.aggregate([
        {
            $match:{
                gid: 8
            }
        },
        {
            $lookup: {
                from: 'comments',
                foreignField: 'gid',
                localField: 'gid',
                as: 'reviews'
            }
        }
    ])
     */
    public Optional<Document> getGameWithReviews(Integer id){

        MatchOperation matchId = Aggregation.match(
            Criteria.where("gid").is(id)
        );

        LookupOperation lookupReviews = Aggregation.lookup(
            "comments", "gid", "gid", "reviews");

        Aggregation pipeline = Aggregation.newAggregation(matchId, lookupReviews);

        List<Document> result = mongoTemplate.aggregate(pipeline, "games", Document.class).getMappedResults();

        if (result.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(result.get(0));
    }
}
