package wanted.preassignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.data.annotation.LastModifiedBy;
import wanted.preassignment.entity.Board.Board;
import wanted.preassignment.entity.Board.BoardCategory;
import wanted.preassignment.entity.User.User;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDetailResponse {
    private Long id;

    private String title;

    private String content;

    private String boardCategory;

    private String createBy;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static BoardDetailResponse boardListFrom(Board board){
        return BoardDetailResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .createBy(board.getUser().getName())
                .createDate(board.getCreateDate())
                .build();
    }

    public static BoardDetailResponse boardDetailFrom(Board board){
        return BoardDetailResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .boardCategory(String.valueOf(board.getBoardCategory()))
                .createBy(board.getUser().getName())
                .createDate(board.getCreateDate())
                .updateDate(board.getUpdateDate())
                .build();
    }

}
