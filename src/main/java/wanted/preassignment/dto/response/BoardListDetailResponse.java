package wanted.preassignment.dto.response;

import lombok.Builder;
import lombok.Data;
import wanted.preassignment.entity.Board.Board;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardListDetailResponse {
    private Long id;

    private String title;

    private String createBy;

    private LocalDateTime createDate;

    public static BoardListDetailResponse boardListDetailFrom(Board board){
        return BoardListDetailResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .createBy(board.getUser().getName())
                .createDate(board.getCreateDate())
                .build();
    }
}
