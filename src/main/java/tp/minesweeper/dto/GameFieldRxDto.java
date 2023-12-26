package tp.minesweeper.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GameFieldRxDto {
    private int width;
    private int height;
    private List<Boolean> mines;
}
