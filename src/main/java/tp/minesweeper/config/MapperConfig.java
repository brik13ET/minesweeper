package tp.minesweeper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tp.minesweeper.dto.GameCellDto;
import tp.minesweeper.dto.GameFieldDto;
import tp.minesweeper.dto.UserCellDto;
import tp.minesweeper.mapper.Mapper;
import tp.minesweeper.model.GameCell;
import tp.minesweeper.model.GameField;
import tp.minesweeper.model.UserCell;
import tp.minesweeper.repository.GameFieldRepository;
import tp.minesweeper.service.FieldService;

@Configuration
public class MapperConfig {

    private GameFieldRepository gameFieldRepository;

    @Bean
    public Mapper<GameFieldDto, GameField>  GameFieldDtoToEntity()
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
    public Mapper<GameField, GameFieldDto> GameFieldEntityToDto()
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
}
