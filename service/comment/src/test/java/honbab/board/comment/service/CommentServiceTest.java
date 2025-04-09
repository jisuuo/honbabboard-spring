package honbab.board.comment.service;

import honbab.board.comment.entity.Comment;
import honbab.board.comment.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import lombok.experimental.ExtensionMethod;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtensionMethod(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    CommentService commentService;

    @Mock
    CommentRepository commentRepository;

    @Test
    @DisplayName("삭제할 댓글이 자식이 있으면, 삭제 표시만 한다")
    void deleteShouldMarkDeletedIfHasChildren() {

        Long articleId = 1L;
        Long commentId = 2L;

        Comment comment = createComment(articleId, commentId);
        given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));
        given(commentRepository.countBy(articleId, commentId, 2L)).willReturn(2L);

        commentService.delete(commentId);

        verify(comment).delete();

    }

    private Comment createComment(Long articleId, Long commentId) {
        Comment comment = mock(Comment.class);
        given(comment.getArticleId()).willReturn(articleId);
        given(comment.getCommentId()).willReturn(commentId);
        return comment;
    }

    private Comment createComment(Long articleId, Long commentId, Long parentCommentId) {
        Comment comment = createComment(articleId, commentId, parentCommentId);
        given(comment.getParentCommentId()).willReturn(parentCommentId);
        return comment;
    }

}