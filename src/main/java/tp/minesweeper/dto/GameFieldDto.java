package tp.minesweeper.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class GameFieldDto {
    @Getter
    protected int id;
    @Getter
    protected int width;
    @Getter
    protected int height;
    @Getter
    protected int mines;
}
