package com.julien.climbers2.service;

import com.julien.climbers2.entities.Comment;
import com.julien.climbers2.entities.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsperRoute(int id){
        return commentRepository.findCommentsByRouteId(id);
    }

    public void addComment(Comment comment){
        commentRepository.save(comment);
    }
}
