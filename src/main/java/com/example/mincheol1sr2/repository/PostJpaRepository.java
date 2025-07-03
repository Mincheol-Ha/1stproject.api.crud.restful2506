package com.example.mincheol1sr2.repository;

import com.example.mincheol1sr2.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findByAuthor(String author);
}
