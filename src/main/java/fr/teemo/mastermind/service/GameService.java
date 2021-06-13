package main.java.fr.teemo.mastermind.service;

import main.java.fr.teemo.mastermind.converter.StringToDigitsConverter;
import main.java.fr.teemo.mastermind.exception.InvalidInputException;
import main.java.fr.teemo.mastermind.exception.MatchingException;
import main.java.fr.teemo.mastermind.model.Digit;
import main.java.fr.teemo.mastermind.validator.InputValidator;
import main.java.fr.teemo.mastermind.validator.MatchValidator;

import java.io.IOException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class GameService {

    private final WritingService writingService;
    private final InputHandler inputHandler;
    private final InputValidator inputValidator;
    private final StringToDigitsConverter stringToDigitsConverter;
    private final Integer digitNumber;
    private final Integer[] acceptedValues;
    private final MatchValidator matchValidator;
    private SortedSet<Digit> secretCode;
    private Integer turnNumber = 1;

    public GameService(WritingService writingService,
                       InputHandler inputHandler,
                       InputValidator inputValidator,
                       MatchValidator matchValidator,
                       StringToDigitsConverter stringToDigitsConverter,
                       Integer digitNumber,
                       Integer[] acceptedValues) {
        this.writingService = writingService;
        this.inputHandler = inputHandler;
        this.inputValidator = inputValidator;
        this.matchValidator = matchValidator;
        this.stringToDigitsConverter = stringToDigitsConverter;
        this.digitNumber = digitNumber;
        this.acceptedValues = acceptedValues;
    }

    public void startGame() throws IOException {
        this.secretCode = this.generateSecretCode();
        handleTurn();
        this.writingService.printWinningSequence(turnNumber);
    }


    private void handleTurn() throws IOException {
        String inputedString;
        Set<Digit> inputedDigits;
        this.writingService.printStartTurn(turnNumber);
        inputedString = this.inputHandler.read();
        try {
            inputValidator.validate(inputedString, this.digitNumber, this.acceptedValues);
        } catch (InvalidInputException e) {
            this.writingService.printError(e);
            this.handleTurn();
        }
        inputedDigits = this.stringToDigitsConverter.toDigits(inputedString);
        try {
            this.matchValidator.validate(inputedDigits, secretCode);
        } catch (MatchingException e) {
            this.writingService.printMatchingError(
                    this.stringToDigitsConverter.toInputedString(e.getDigits()),
                    this.stringToDigitsConverter.toMatchingString(e.getDigits())
            );
            turnNumber = turnNumber + 1;
            this.handleTurn();
        }
    }


    private SortedSet<Digit> generateSecretCode() {
        SortedSet<Digit> newSecretCode = new TreeSet<>(Digit::compareTo);
        IntStream.range(0, this.digitNumber).forEach(i -> newSecretCode.add(new Digit(ThreadLocalRandom.current().nextInt(1, 10), i + 1)));
        return newSecretCode;
    }
}
