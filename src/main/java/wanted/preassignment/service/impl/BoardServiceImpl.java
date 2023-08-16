package wanted.preassignment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import wanted.preassignment.entity.User.User;
import org.springframework.stereotype.Service;
import wanted.preassignment.dto.request.BoardDetailRequest;
import wanted.preassignment.entity.Board.Board;
import wanted.preassignment.entity.Board.BoardCategory;
import wanted.preassignment.exception.CustomException;
import wanted.preassignment.exception.ErrorCode;
import wanted.preassignment.repository.BoardRepository;
import wanted.preassignment.repository.UserRepository;
import wanted.preassignment.service.BoardService;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public Board boardDetail(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_BOARD)
        );
    }

    @Transactional
    @Override
    public Long createBoard(User user, BoardDetailRequest boardDetailRequest) {

        User findUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_ID)
        );

        return boardRepository.save(
            Board.builder()
                    .title(boardDetailRequest.getTitle())
                    .content(boardDetailRequest.getContent())
                    .boardCategory(BoardCategory.valueOf(boardDetailRequest.getBoardCategory()))
                    .user(findUser)
                    .build()
        ).getId();
    }

    @Transactional
    @Override
    public Long updateBoard(User user, Long boardId, BoardDetailRequest boardDetailRequest) {

        User findUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_ID)
        );

        Board findBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_BOARD)
        );

        return boardRepository.save(
                Board.builder()
                        .id(findBoard.getId())
                        .title(boardDetailRequest.getTitle())
                        .content(boardDetailRequest.getContent())
                        .boardCategory(BoardCategory.valueOf(boardDetailRequest.getBoardCategory()))
                        .user(findUser)
                        .build()
        ).getId();
    }

    @Transactional
    @Override
    public Long deleteBoard(User user, Long boardId) {

        User findUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_ID)
        );

        Board findBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_BOARD)
        );

        boardRepository.delete(findBoard);

        return boardId;
    }


}
