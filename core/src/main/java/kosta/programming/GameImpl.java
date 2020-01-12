package kosta.programming;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
@Getter
public class GameImpl implements Game {

    // == private fields ==
    @Getter (AccessLevel.NONE)
    private final NumberGenerator numberGenerator;
    private final int guessCount;

    private int number;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Setter
    private int guess;

    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    // == init method ==
    @Override
    @PostConstruct
    public void reset() {
        guess = numberGenerator.getMinNumber();
        smallest = numberGenerator.getMinNumber();
        biggest = numberGenerator.getMaxNumber();
        remainingGuesses = guessCount;
        number = numberGenerator.randomNum();
        log.debug("Number is {}", number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("in pre destroy");
    }

    // == public methods ==

        @Override
    public void check() {
        checkValidNumberRange();

        if(validNumberRange){

            if(guess > number){
                biggest = guess - 1;
            }
            if(guess < number){
                smallest = guess + 1;
            }
        }
        remainingGuesses--;
    }


       @Override
    public boolean isGameLost() {
        return !isGamWon() && remainingGuesses <= 0;
    }

    @Override
    public boolean isGamWon() {
        return guess == number;
    }


    // == private methods ==

    private void checkValidNumberRange() {
        validNumberRange = guess >= smallest && guess <= biggest;
    }

}
