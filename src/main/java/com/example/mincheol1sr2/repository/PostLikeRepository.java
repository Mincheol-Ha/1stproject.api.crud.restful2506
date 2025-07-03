package com.example.mincheol1sr2.repository;

import com.example.mincheol1sr2.entity.CommentLikeEntity;
import com.example.mincheol1sr2.entity.PostEntity;
import com.example.mincheol1sr2.entity.PostLikeEntity;
import com.example.mincheol1sr2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Integer> {

    boolean existsByUserAndPost(UserEntity user, PostEntity post);
    void deleteByUserAndPost(UserEntity user, PostEntity post);

    Integer countByPost(PostEntity post);

    Optional<PostLikeEntity> findByUserAndPost(UserEntity user, PostEntity post);
}
