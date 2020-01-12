package kosta.programming.service;

import kosta.programming.Game;
import kosta.programming.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    private final Game game;
    private final MessageGenerator messageGenerator;

    @Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }
    @PostConstruct
    public void init(){
        log.info(messageGenerator.getMainMessage());
        log.info("number is "+game.getNumber());
    }

    @Override
    public boolean isGameOver (){
        if(game.isGameLost()){
            return true;
        }else if(game.isGamWon()){
            return true;
        }
        return false;
    }

    @Override
    public String getMainMessage() {
        return messageGenerator.getMainMessage();
    }

    @Override
    public String getResultMessage() {
        return messageGenerator.getResultMessage();
    }

    @Override
    public void checkGuess(int num) {
        game.setGuess(num);
        game.check();
    }

    @Override
    public void reset() {
        game.reset();
    }
}
