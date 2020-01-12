package kosta.programming.contollers;

import kosta.programming.service.GameService;
import kosta.programming.util.AttributeNames;
import kosta.programming.util.Mappings;
import kosta.programming.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping (Mappings.PLAY)
    public String game(Model model){
        model.addAttribute(AttributeNames.MAIN_MESSAGE, gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());

        if(gameService.isGameOver()){
            return ViewNames.GAME_OVER;
        }
        return ViewNames.PLAY;
    }

    @PostMapping (Mappings.PLAY)
    public String processGame(@RequestParam int guess){
        log.info("guess = {}", guess);
        gameService.checkGuess(guess);
        return Mappings.REDIRECT_PLAY;
    }

    @GetMapping(Mappings.RESTART)
    public String gameOver(Model model){
        gameService.reset();
        return Mappings.REDIRECT_PLAY;
    }
}
