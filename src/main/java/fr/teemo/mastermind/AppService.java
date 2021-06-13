package main.java.fr.teemo.mastermind;

import main.java.fr.teemo.mastermind.converter.StringToDigitsConverter;
import main.java.fr.teemo.mastermind.exception.PrintingException;
import main.java.fr.teemo.mastermind.exception.ReadingException;
import main.java.fr.teemo.mastermind.service.GameService;
import main.java.fr.teemo.mastermind.service.InputHandler;
import main.java.fr.teemo.mastermind.service.WritingService;
import main.java.fr.teemo.mastermind.validator.InputValidator;
import main.java.fr.teemo.mastermind.validator.MatchValidator;

import java.io.IOException;

public class AppService {

    private final WritingService writingService;

    private final GameService gameService;
    private final Integer digitNumber = 4;


    public AppService() {
        InputValidator inputValidator = new InputValidator();
        MatchValidator matchValidator = new MatchValidator();
        InputHandler inputHandler = new InputHandler(System.in);
        StringToDigitsConverter converter = new StringToDigitsConverter();
        Integer[] acceptedValues = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        writingService = new WritingService(System.out);
        gameService = new GameService(
                writingService,
                inputHandler,
                inputValidator,
                matchValidator,
                converter,
                digitNumber,
                acceptedValues);
    }

    public void launchApp() throws IOException {
        writingService.printTitle();
        writingService.printRules(this.digitNumber);
        try {
            this.gameService.startGame();
        } catch (PrintingException | ReadingException e) {
            writingService.printError(e);
        }
    }

}
