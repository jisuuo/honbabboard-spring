package honbab.board.comment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "comment")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    private Long commentId;
    private String content;
    private Long parentCommentId;
    private Long articleId;
    private Long writerId;
    private Boolean deleted;
    private LocalDateTime createAt;


    public static Comment create(Long commentId, String content, Long parentCommentId, Long articleId, Long writerId) {
        Comment comment = new Comment();
        comment.commentId = commentId;
        comment.content = content;
        comment.parentCommentId = parentCommentId == null ? commentId : parentCommentId; // 상위 댓글 없는 경우 자기로 지정
        comment.articleId = articleId;
        comment.writerId = writerId;
        comment.deleted = false;
        comment.createAt = LocalDateTime.now();

        return comment;
    }
    // 1Depth 댓글 확인한느 isRoot 메소드 정해주기
    public boolean isRoot() {
        return parentCommentId.longValue() == commentId;
    }

    public void delete() {
        deleted = true;
    }

}
