package tp.minesweeper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class WWWController {

    @GetMapping("/")
    public RedirectView redirIndex(RedirectAttributes attr)
    {
        attr.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        return new RedirectView("/reg.html");
    }

//    @GetMapping("/game{id}")
//    public String game(@PathVariable("id") int userGameId, Model model)
//    {
//        return "reg.html";
//    }
}
