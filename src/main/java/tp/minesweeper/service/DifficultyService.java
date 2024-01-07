package tp.minesweeper.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.minesweeper.dto.DifficultyDto;
import tp.minesweeper.mapper.Mapper;
import tp.minesweeper.model.Difficulty;
import tp.minesweeper.repository.DifficultyRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DifficultyService {
    private final DifficultyRepository difficultyRepository;
    @Autowired
    private final Mapper<Difficulty, DifficultyDto> difficultyToDto;
    @Autowired
    private final Mapper<DifficultyDto, Difficulty> difficultyToEntity;

    public Optional<DifficultyDto> getDifficulty(Integer num)
    {
        return difficultyRepository.findById(num).map(difficultyToDto::map);
    }

        public List<DifficultyDto> getDifficultyAll()
    {
        return difficultyRepository.findAll()
                .stream()
                .map(difficultyToDto::map)
                .toList();
    }

    public void setDifficulty(DifficultyDto dif)
    {
        difficultyRepository.findById(dif.getId()).ifPresentOrElse(dbo -> {
            dbo.setName(dif.getName());
            dbo.setLives(dif.getLives());
            dbo.setWidth(dif.getWidth());
            dbo.setHeight(dif.getHeight());
            dbo.setMines(dif.getMines());
            difficultyRepository.save(dbo);
        }, () ->
                difficultyRepository.save(difficultyToEntity.map(dif))
        );
    }
}
