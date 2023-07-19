package sg.edu.nus.iss.Workshop27.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.Workshop27.model.Comment;

@Repository
public class CommentRepo {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    /*
    db.comments.insert({
        user: "pekopop",
        rating: 9,
        c_text: "nice game",
        gid: 20437,
        posted "date"
        name: "Lords of Vegas"
    })
     */
    public void insertReview(Comment comment){
        Document doc = new Document();
        doc.put("user", comment.getUser());
        doc.put("rating", comment.getRating());
        doc.put("c_text", comment.getComment());
        doc.put("gid", comment.getGid());
        doc.put("posted", comment.getPosted());
        doc.put("name", comment.getName());

        mongoTemplate.insert(doc, "comments");
    }

    /*
     db.comments.aggregate([
        {
            $match: { gid: 4 }  
        },
        {
            $project:{ rating: 1, gid: 1 }
        },
        {
            $group: {
                _id: "$gid",
                average: {$avg: "$rating"} 
            }
        }
    ])
     */
    public List<Document> getAverageRating(int gid){

        MatchOperation matchGid = Aggregation.match(
            Criteria.where("gid").is(gid)
        );

        ProjectionOperation projectFields = Aggregation.project(
            "rating", "gid")
            .andExclude("_id");

        GroupOperation group = Aggregation.group("gid")
            .avg("rating").as("average");

        Aggregation pipeline = Aggregation.newAggregation(matchGid, projectFields, group);

        return mongoTemplate.aggregate(pipeline, "comments", Document.class).getMappedResults();
    }
}
