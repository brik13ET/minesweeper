package tp.minesweeper.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tp.minesweeper.dto.*;
import tp.minesweeper.service.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class APIController {

    private final CellService cellService;
    private final FieldService fieldService;
    private final UserService userService;

    @GetMapping("/isUserExistsID")
    @ResponseBody
    ResponseEntity isUserExistsID(Integer id)
    {
        return new ResponseEntity<>(
            userService.findById(id).isPresent(),
            HttpStatus.OK
        );
    }



    @GetMapping("/isUserExists")
    @ResponseBody
    ResponseEntity isUserExistsLogin(String login)
    {
        return new ResponseEntity<>(
            userService.findByLogin(login).isPresent(),
            HttpStatus.OK
        );
    }


    @GetMapping("/isFieldExists")
    @ResponseBody
    ResponseEntity isFieldExists(Integer id)
    {
        return new ResponseEntity<>(
            fieldService.findGameFieldById(id).isPresent(),
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
    ResponseEntity  isCorrectPass(@RequestParam String login, @RequestParam String pass)
    {
        var dbo = userService.findByLogin(login);
        if (dbo.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        var usr = dbo.get();
        return new ResponseEntity(usr.getPassword().equals(pass), HttpStatus.OK);
    }

    @GetMapping("/getUserId")
    @ResponseBody
    ResponseEntity getUserId(@RequestParam() String login, @RequestParam String pass)
    {
        var dbo = userService.findByLogin(login);
        if (dbo.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        var usr = dbo.get();
        if (!usr.getPassword().equals(pass))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(usr.getId(), HttpStatus.OK);
    }


    @GetMapping("/make_coffee")
    @ResponseBody
    ResponseEntity  make_coffee()
    {
        return new ResponseEntity("I_AM_A_TEAPOT", HttpStatus.I_AM_A_TEAPOT);
    }
    @PostMapping("/newGameField")
    @ResponseBody
    ResponseEntity  newGameField(@RequestBody GameFieldRxDto field)
    {
        try
        {
            var gameField = fieldService
                    .newGameFieldCells(
                            field.getWidth(),
                            field.getHeight(),
                            field.getMines()
                                    .toArray(
                                            new Boolean[
                                                    field.getWidth()*field.getHeight()
                                                    ]
                                    )
                    );
            return new ResponseEntity(gameField, HttpStatus.OK);
        } catch (ParameterOutOfBoundsException exception)
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/newUser")
    @ResponseBody
    ResponseEntity  newUser(@RequestBody UserDto usr)
    {
        if (userService.findByLogin(usr.getLogin()).isPresent())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        userService.newUser(usr.getLogin(), usr.getPass());
        if (userService.findByLogin(usr.getLogin()).isPresent())
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
    List<Integer> getGameMapsIds(@RequestParam(defaultValue = "10") int MapsCount)
    {
        var gms = fieldService.findAllGames();
        return gms.stream()
                        .map(gm -> gm.getId())
                        .collect(Collectors.toList());
    }

    @GetMapping("/getUserSaves")
    @ResponseBody
    ResponseEntity  getUserSaves(int uid)
    {
        var usr = userService.findById(uid);
        if (usr.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        var saves = fieldService.findAllByUser(usr.get());
        return new ResponseEntity(saves, HttpStatus.OK);
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
    ResponseEntity<GameFieldDto> getGameFieldParams(@RequestParam(name = "field_id") int gid)
    {
         var gf = fieldService.findGameFieldById(gid);
         if (gf.isEmpty())
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         return new ResponseEntity<>( gf.get(), HttpStatus.OK);

    }
}
