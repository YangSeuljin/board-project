package com.example.boardproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})

@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private Article article; // 게시글 (ID)

    @Setter
    @Column(nullable = false, length = 100)
    private String content; // 본문

//    @CreatedDate
//    @Column(nullable = false)
//    private LocalDateTime createdAt; //생성일시
//
//    @CreatedDate
//    @Column(nullable = false)
//    private String createdBy; //생성자
//
//    @LastModifiedDate
//    @Column(nullable = false)
//    private LocalDateTime modifiedAt; //수정일시
//
//    @LastModifiedBy
//    @Column(nullable = false)
//    private String modifiedBy; //수정자

    protected ArticleComment() {
    }

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
