package wanted.preassignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import wanted.preassignment.dto.response.BoardListResponse;
import wanted.preassignment.entity.Board.Board;
import wanted.preassignment.entity.User.User;
import org.springframework.web.bind.annotation.*;
import wanted.preassignment.config.annotation.LoginUser;
import wanted.preassignment.dto.request.BoardDetailRequest;
import wanted.preassignment.dto.response.BoardDetailResponse;
import wanted.preassignment.dto.response.ResultResponse;
import wanted.preassignment.service.BoardService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("")
    public BoardListResponse boardList(@LoginUser User user,
                                       @RequestParam(value = "page",defaultValue = "1") Integer page,
                                       @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(page == null) page = 1;

        Pageable pageable = PageRequest.of(page-1,size, Sort.by("id"));

        return BoardListResponse.boardPageFrom(boardService.boardList(pageable));
    }

    @GetMapping("/{boardId}")
    public BoardDetailResponse boardDetail(@PathVariable(value = "boardId") Long boardId) {
        return BoardDetailResponse.boardDetailFrom(boardService.boardDetail(boardId));
    }

    @PostMapping("")
    public ResponseEntity<ResultResponse> createBoard(@LoginUser User user, @RequestBody BoardDetailRequest boardDetailRequest){
        Long id = boardService.createBoard(user,boardDetailRequest);

        return ResponseEntity.ok(new ResultResponse(id, "게시글을 등록하였습니다."));
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<ResultResponse> updateBoard(@LoginUser User user,
                                                      @PathVariable(value = "boardId") Long boardId,
                                                      @RequestBody BoardDetailRequest boardDetailRequest) {
        Long id = boardService.updateBoard(user,boardId,boardDetailRequest);

        return ResponseEntity.ok(new ResultResponse(id, "게시글을 수정하였습니다."));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResultResponse> deleteBoard(@LoginUser User user,
                                                      @PathVariable(value = "boardId") Long boardId) {
        Long id = boardService.deleteBoard(user,boardId);

        return ResponseEntity.ok(new ResultResponse(id, "게시글을 삭제하였습니다."));
    }

}
