package tp.minesweeper.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
public class UserFieldId implements Serializable {

    @Getter
    @OneToOne(targetEntity = GameCell.class)
    protected GameField gameField;

    @Getter
    @OneToOne(targetEntity = User.class)
    protected User user;
}
