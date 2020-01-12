package kosta.programming;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Getter
public class NumberGeneratorImpl implements NumberGenerator {

    @Getter (AccessLevel.NONE)
    private static Random random = new Random();

    private final int maxNumber;
    private final int minNumber;

    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    @Override
    public int randomNum() {
        return random.nextInt((maxNumber - minNumber) + 1) + minNumber;

    }

}
