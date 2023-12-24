package tp.minesweeper.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.minesweeper.service.CellService;
import tp.minesweeper.service.FieldService;
import tp.minesweeper.service.UserService;

@RestController
@AllArgsConstructor
public class APIController {

    private final CellService cellService;
    private final FieldService fieldService;
    private final UserService userService;

    @GetMapping("/isUserExists")
    @ResponseBody
    ResponseEntity isUserExists(Integer id)
    {
        return new ResponseEntity<>(
            userService.findById(id).isPresent(),
            HttpStatus.OK
        );
    }

    @GetMapping("/isFieldExists")
    @ResponseBody
    ResponseEntity isFieldExists(Integer id)
    {
        return new ResponseEntity<>(
            fieldService.findById(id).isPresent(),
            HttpStatus.OK
        );
    }

    @PutMapping("/promise")
    ResponseEntity promise(Integer uid, Integer uf_id, Integer pos_x, Integer pos_y)
    {
        var usr = userService.findById(uid);
        if (usr.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        fieldService.findById().
        return null;
    }
    @GetMapping("/isCorrectPass")
    @ResponseBody
    ResponseEntity  isCorrectPass()
    {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/make_coffee")
    @ResponseBody
    ResponseEntity  make_coffee()
    {
        return new ResponseEntity("I_AM_A_TEAPOT", HttpStatus.I_AM_A_TEAPOT);
    }
    @PostMapping("/newGameField")
    @ResponseBody
    ResponseEntity  newGameField(Integer width, Integer height, Integer mines)
    {
        fieldService.newGameField(width, height, mines);
        fieldService.findById()
        return
    }
    @PostMapping("/newUser")
    @ResponseBody
    ResponseEntity  newUser(String login, String pass)
    {
        if (userService.findByLogin(login).isPresent())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        userService.newUser(login,pass);
        if (userService.findByLogin(login).isPresent())
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.GATEWAY_TIMEOUT);
    }
    @PostMapping("/newUserField")
    @ResponseBody
    ResponseEntity  newUserField()
    {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
    @GetMapping("/getGameMapsIds")
    @ResponseBody
    ResponseEntity  getGameMapsIds(String login)
    {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
    @GetMapping("/getUserSaves")
    @ResponseBody
    ResponseEntity  getUserSaves()
    {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
    @GetMapping("/getUserCell")
    @ResponseBody
    ResponseEntity  getUserCell()
    {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
    @GetMapping("/getUserLogin")
    @ResponseBody
    ResponseEntity  getUserLogin(Integer uid)
    {
        var usr = userService.findById(uid);
        if (usr.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(usr.get().getLogin(), HttpStatus.OK);
    }
    @GetMapping("/getGameFieldParams")
    @ResponseBody
    ResponseEntity  getGameFieldParams()
    {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
}
