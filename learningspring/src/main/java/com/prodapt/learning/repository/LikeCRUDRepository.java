package com.prodapt.learning.repository;



import org.springframework.data.repository.CrudRepository;

import com.prodapt.learning.*;
import com.prodapt.learning.entity.LikeId;
import com.prodapt.learning.entity.LikeRecord;
import com.prodapt.learning.entity.Post;

public interface LikeCRUDRepository extends CrudRepository<LikeRecord, LikeId>{
  public Integer countByLikeIdPost(Post post);
}