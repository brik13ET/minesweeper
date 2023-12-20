package tp.minesweeper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CellId implements Serializable
{
	@Getter
	@Column(name = "field_id", nullable = false)
	private int fieldId;

	@Getter
	private int posX;

	@Getter
	private int posY;

	@Override
	public String toString() {
		return String.format(
			"fieldId: %d\n"+"posX: %d\n"+"posY: %d\n",
			this.fieldId,this.posX,this.posY
		);
	}
}
