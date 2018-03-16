package com.julien.climbers2.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Integer>{

    List<Comment> findCommentsByRouteId(int id);
}
