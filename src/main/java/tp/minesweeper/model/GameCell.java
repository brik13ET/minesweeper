package tp.minesweeper.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "game_cell")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class GameCell {

	@Id
	@Getter
	@Setter
	@EmbeddedId
	CellId cellId;

	@Getter
	@Setter
	Boolean planted;
}
