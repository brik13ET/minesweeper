package tp.minesweeper.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"user\"")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue
    @Getter
    private int id;

    @Getter
    @Column(unique = true)
    private String login;

    @Getter
    @Setter
    private String password;

    @Builder
    public User(String login, String password)
    {
        this.login = login;
        this.password = password;
    }
}
