package com.prodapt.learning.repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prodapt.learning.entity.Comment;
import com.prodapt.learning.entity.CommentId;
import com.prodapt.learning.entity.CommentRecord;
//import org.springframework.data.repository.CrudRepository;
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	//List<Comment> findByPostId(int postId);
	//@Query(value = "select * from `like_record` where post_id = ?1", nativeQuery=true)
	//List<Comment> findCommentRecordsByPostId(int postId);
	List<Comment> findByPostId(int postId);
}
