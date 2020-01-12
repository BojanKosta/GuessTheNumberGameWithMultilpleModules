package kosta.programming.service;

public interface GameService {

    boolean isGameOver();
    String getMainMessage();
    String getResultMessage();
    void checkGuess(int num);
    void reset();
}
