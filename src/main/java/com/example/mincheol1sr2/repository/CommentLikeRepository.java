package com.example.mincheol1sr2.repository;

import com.example.mincheol1sr2.entity.CommentEntity;
import com.example.mincheol1sr2.entity.CommentLikeEntity;
import com.example.mincheol1sr2.entity.PostEntity;
import com.example.mincheol1sr2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Integer> {

    boolean existsByUserAndCommentAndPost(UserEntity user, CommentEntity comment, PostEntity post);

    void deleteByCommentAndPost(CommentEntity comment, PostEntity post);
}
