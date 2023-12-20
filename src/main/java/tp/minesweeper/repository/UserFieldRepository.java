package tp.minesweeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tp.minesweeper.model.GameField;
import tp.minesweeper.model.User;
import tp.minesweeper.model.UserField;
import tp.minesweeper.model.UserFieldId;

import java.util.Optional;
import java.util.Set;

public interface UserFieldRepository extends JpaRepository<UserField,Integer>
{

    @Query(value = "SELECT uf FROM UserField uf WHERE uf.id.user.id = :uid and uf.id.gameField.id = :gf_id")
    Optional<UserField> findByUidAndGFid(@Param("uid") Integer uid, @Param("gf_id") Integer gfid);

    @Query(value = "SELECT uf FROM UserField uf WHERE uf.id.user.id = ?1")
    Set<UserField> findAllByUid(@Param("uid") Integer usr);
}
