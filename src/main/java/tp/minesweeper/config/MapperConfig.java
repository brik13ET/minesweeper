package tp.minesweeper.config;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tp.minesweeper.dto.*;
import tp.minesweeper.mapper.Mapper;
import tp.minesweeper.model.*;
import tp.minesweeper.repository.GameFieldRepository;
import tp.minesweeper.repository.UserRepository;

import java.util.Map;

@Configuration
public class MapperConfig {

    private GameFieldRepository gameFieldRepository;
    private UserRepository userRepository;

    @Bean
    Mapper<GameFieldDto, GameField>  GameFieldDtoToEntity()
    {
        return obj -> {
            if (obj.getId() != 0)
            {
                var dbo = gameFieldRepository.findById(obj.getId());
                if (dbo.isPresent())
                    return dbo.get( );
            }
            GameField entity = new GameField(obj.getWidth(), obj.getHeight(), obj.getMines());
            gameFieldRepository.save(entity);
            return entity;
        };
    }

    @Bean
    Mapper<GameField, GameFieldDto> GameFieldEntityToDto()
    {
        return obj ->
            GameFieldDto.builder()
                    .width(obj.getWidth())
                    .height(obj.getHeight())
                    .mines(obj.getMines())
                    .id(obj.getId())
                    .build();
    }

    @Bean
    Mapper<GameCell, GameCellDto> GameCellEntityToDto()
    {
        return obj ->
                GameCellDto.builder()
                        .posX(obj.getCellId().getPosX())
                        .posY(obj.getCellId().getPosX())
                        .planted(obj.getPlanted())
                        .build();
    }

    @Bean
    Mapper<UserCell, UserCellDto> UserCellEntityToDto()
    {
        return obj -> UserCellDto.builder()
                .posX(obj.getCellId().getPosX())
                .posY(obj.getCellId().getPosY())
                .opened(obj.isOpened())
                .build();
    }

    @Bean
    Mapper<Difficulty, DifficultyDto> difficultyToDto()
    {
        return obj -> DifficultyDto.builder()
                .id(obj.getId())
                .name(obj.getName())
                .width(obj.getWidth())
                .height(obj.getHeight())
                .mines(obj.getMines())
                .lives(obj.getLives())
                .build();
    }

    @Bean
    Mapper<DifficultyDto, Difficulty> difficultyToEntity()
    {
        return obj ->
                new Difficulty(
                        obj.getId(),
                        obj.getName(),
                        obj.getWidth(),
                        obj.getHeight(),
                        obj.getMines(),
                        obj.getLives()
                );
    }

    @Bean
    Mapper<UserDto, User> userToEntity()
    {
        return obj -> {
            var dbo = userRepository.findByLogin(obj.getLogin());
            if (dbo.isEmpty())
                return null;
            else return dbo.get();
        };
    }

    @Bean
    Mapper<User, UserDto> userToDto()
    {
        return obj -> UserDto.builder()
                .login(obj.getLogin())
                .pass(obj.getPassword())
                .build();
    }
}
