package sg.edu.nus.iss.Workshop27.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.Workshop27.model.Comment;
import sg.edu.nus.iss.Workshop27.repository.CommentRepo;
import sg.edu.nus.iss.Workshop27.repository.GameRepo;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepo commentRepo;

    @Autowired 
    private GameRepo gameRepo;

    public void postComment(Comment comment){

        Document game = gameRepo.getGameById(comment.getGid()).get();
        String name = game.getString("name");
        comment.setName(name);
        commentRepo.insertReview(comment);

    }

    public double getAverage(int gid){
        Document doc = commentRepo.getAverageRating(gid).get(0);
        return doc.getDouble("average");
    }

}
