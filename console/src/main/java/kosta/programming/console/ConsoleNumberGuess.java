package kosta.programming.console;

import kosta.programming.Game;
import kosta.programming.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Slf4j
public class ConsoleNumberGuess {

    private final Game game;
    private final MessageGenerator messageGenerator;

    @Autowired
    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    @EventListener (ContextRefreshedEvent.class)
    public void start (){
        log.info("///////////");
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            int num = scanner.nextInt();
            scanner.nextLine();

            game.setGuess(num);
            game.check();

            if(game.isGamWon() || game.isGameLost()){
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Do you want to quit or continue playing? \n" +
                        "Yes/y  or No/n");
                String gameStatus = scanner.nextLine().trim();
                if(gameStatus.toLowerCase().equals("n")){
                    break;
                }
                game.reset();
            }

        }

    }

}
