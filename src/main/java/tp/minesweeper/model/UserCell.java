package tp.minesweeper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "user_cell")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserCell {

	@Id
	@Getter
	@Setter
	CellId cellId;

	@Getter
	@Setter
	boolean opened;
}
