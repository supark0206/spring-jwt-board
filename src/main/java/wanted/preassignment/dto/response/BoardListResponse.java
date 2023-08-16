package wanted.preassignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import wanted.preassignment.entity.Board.Board;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardListResponse {
    private List<BoardDetailResponse> boardList;
    private int totalPages;

    public static BoardListResponse boardPageFrom(Page<Board> boardPage) {
        List<BoardDetailResponse> boardList =
                boardPage.getContent()
                .stream()
                .map(BoardDetailResponse::boardListFrom)
                .collect(Collectors.toList());

        return BoardListResponse.builder()
                .boardList(boardList)
                .totalPages(boardPage.getTotalPages())
                .build();
    }
}
