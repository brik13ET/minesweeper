package tp.minesweeper.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper <D,E>{
    E map(D obj);
}
