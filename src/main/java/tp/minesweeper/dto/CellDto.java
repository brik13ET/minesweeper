package tp.minesweeper.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
// Only UserCell allower to transmit
public class CellDto {
    int posX, posY;
    boolean value;
}
