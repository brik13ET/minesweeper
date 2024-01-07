package tp.minesweeper.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;

import java.util.Arrays;

@Entity
@Table(name = "game_field")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GameField {

    @Id
    @GeneratedValue
    protected int id;
    protected int width;
    protected int height;
    protected int mines;
    @Builder
    public GameField(
        int width,
        int height,
        int mines
    )
    {
        if (
            width  > 20||
            width  < 4 ||
            height > 20||
            height < 4 ||
            mines > (width * height * 0.2) ||
            mines < (width * height * 0.04)
        )
        {
            throw new ParameterOutOfBoundsException(
                "Один из параметров поля не находится в диапазоне",
                new Throwable()
            );
        }
        this.width  = width;
        this.height = height;
        this.mines  = mines;
    }

    public GameField(int width, int height, GameCell[] mines)
    {
        if (mines.length != width * height)
            throw new ParameterOutOfBoundsException("Количество ячеек на поле не равно площади поля", new Throwable());

        var mines_count =
                Arrays.stream(mines)
                    .filter(gameCell -> gameCell.getPlanted() )
                    .count();
        if (
                mines_count > width * height * 0.2 ||
                mines_count < width * height * 0.04
        )
            throw new ParameterOutOfBoundsException("Количество заминированных ячеек на поле не входит в диапазон (4% - 20%)", new Throwable());

    }
}
