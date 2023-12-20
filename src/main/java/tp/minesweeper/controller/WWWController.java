package tp.minesweeper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class WWWController {

    @GetMapping("/")
    public RedirectView redirIndex(RedirectAttributes attr)
    {
        return new RedirectView("/reg.html");
    }
}
