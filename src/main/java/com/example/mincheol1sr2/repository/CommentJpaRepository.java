package com.example.mincheol1sr2.repository;

import com.example.mincheol1sr2.entity.CommentEntity;
import com.example.mincheol1sr2.entity.PostEntity;
import com.example.mincheol1sr2.entity.PostLikeEntity;
import com.example.mincheol1sr2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentEntity, Integer> {

    List<CommentEntity> findByPostId(Integer postId);

    Optional<CommentEntity> findByAuthorAndPost(UserEntity author, PostEntity post);
}
