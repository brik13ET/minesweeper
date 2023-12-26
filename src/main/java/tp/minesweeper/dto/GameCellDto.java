package tp.minesweeper.dto;

import lombok.Builder;
import lombok.Data;
import tp.minesweeper.model.CellId;

@Data
@Builder
public class GameCellDto {

	private int posX;
	private int posY;

    private Boolean planted;
}
