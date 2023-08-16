package wanted.preassignment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardDetailRequest {
    private String title;

    private String content;

    private String boardCategory;
}
