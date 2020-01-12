package kosta.programming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class MessageGeneratorImpl implements MessageGenerator {

    // == constants ==
    public static final String MAIN_MESSAGE = "game.main.message";
    public static final String WIN = "game.win";
    public static final String LOSE = "game.lose";
    public static final String INVALID_RANGE = "game.invalid.range";
    public static final String FIRST_GUESS = "game.first.guess";
    public static final String HIGHER = "game.higher";
    public static final String LOWER = "game.lower";
    public static final String REMAINING = "game.remaining";

    // == private fields ==
    private final Game game;
    private final MessageSource messageSource;

    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    // == public methods ==
    @PostConstruct
    public void init() {
        log.info("game = {}", game);
    }

    @Override
    public String getMainMessage() {
//        return "Guess the number between " + game.getSmallest()+
//                " and " + game.getBiggest() + "?";
        return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getBiggest());
    }

    @Override
    public String getResultMessage() {

        if (game.isGameLost()) {
            return getMessage(LOSE);
        } else if (game.isGamWon()) {
            return getMessage(WIN);
        } else if (!game.isValidNumberRange()) {
            return getMessage(INVALID_RANGE);
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return getMessage(FIRST_GUESS);
        } else {
            String description = getMessage(LOWER);

            if (game.getGuess() > game.getNumber()) {
                description = getMessage(HIGHER);
            }

            return getMessage(REMAINING, description, game.getRemainingGuesses());
        }

    }

    private String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
