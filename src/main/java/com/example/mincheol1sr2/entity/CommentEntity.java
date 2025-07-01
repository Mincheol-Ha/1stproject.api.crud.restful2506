package com.example.mincheol1sr2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "comment_id", nullable = false)
        private Integer id;

        private String content;

        private String author;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_id", nullable = false)
        private PostEntity post; // 게시글 연관관계

        @CreatedDate
        @Column(updatable = false)
        private LocalDateTime createAt;

        @LastModifiedDate
        private LocalDateTime updateAt;

}
