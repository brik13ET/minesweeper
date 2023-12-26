package tp.minesweeper.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
// Only UserCell allower to transmit
public class UserCellDto {
    int posX, posY;
    boolean opened;
}
