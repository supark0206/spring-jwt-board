package wanted.preassignment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wanted.preassignment.entity.User.User;
import wanted.preassignment.dto.request.BoardDetailRequest;
import wanted.preassignment.entity.Board.Board;

import java.util.List;

public interface BoardService {
    public Page<Board> boardList(Pageable pageable);

    public Board boardDetail(Long boardId);

    public Long createBoard(User user, BoardDetailRequest boardDetailRequest);

    public Long updateBoard(User user,Long boardId, BoardDetailRequest boardDetailRequest);

    public Long deleteBoard(User user,Long boardId);

}
