package tp.minesweeper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tp.minesweeper.repository.CellRepository;

@Service
@AllArgsConstructor
public class CellService {
    private final CellRepository cellRepository;
}
