package tp.minesweeper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
//@Table(name = "difficulty")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Difficulty {
    @Id
//    @GeneratedValue
    private int id;
    private String name;
    private int width;
    private int height;
    private int mines;
    private int lives;
}
