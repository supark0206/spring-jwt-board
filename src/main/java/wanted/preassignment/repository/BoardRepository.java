package wanted.preassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.preassignment.entity.Board.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
