package tp.minesweeper.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
public class DifficultyDto {
    private int id;
    private String name;
    private int width;
    private int height;
    private int mines;
    private int lives;
}
