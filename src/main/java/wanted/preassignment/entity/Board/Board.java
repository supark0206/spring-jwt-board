package wanted.preassignment.entity.Board;

import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import wanted.preassignment.entity.BaseTimeEntity;
import wanted.preassignment.entity.User.User;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
@EnableJpaAuditing
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String content;

    @Enumerated(value = EnumType.STRING)
    private BoardCategory boardCategory;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
