package tp.minesweeper.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserFieldId implements Serializable {

    @Getter
    @OneToOne(targetEntity = GameCell.class)
    protected GameField gameField;

    @Getter
    @ManyToOne(targetEntity = User.class)
    protected User user;
}
