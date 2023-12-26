package tp.minesweeper.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;

@Entity
@Table(name = "UserField")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserField {

    @EmbeddedId
    protected UserFieldId id;

    @Getter
    protected int width;

    @Getter
    protected int height;

    @Getter
    @Setter
    protected int mines;

    @Builder
    public UserField(
            User user,
            GameField gameField,
            int width,
            int height,
            int mines
    ) {
        if (
                width > 20 ||
                        width < 4 ||
                        height > 20 ||
                        height < 4 ||
                        mines > (width * height * 0.2) ||
                        mines < (width * height * 0.04)
        ) {
            throw new ParameterOutOfBoundsException(
                    "Один из параметров поля не находится в диапазоне",
                    new Throwable()
            );
        }
        this.id = new UserFieldId(gameField, user);
        this.width = width;
        this.height = height;
        this.mines = mines;
    }
}
