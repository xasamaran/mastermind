package main.java.fr.teemo.mastermind.service;

import main.java.fr.teemo.mastermind.exception.PrintingException;
import main.java.fr.teemo.mastermind.model.Digit;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Set;

public class WritingService {

    private final BufferedWriter writer;

    public WritingService(OutputStream outputStream) {
        this.writer = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    public void printWinningSequence(int attempCounter) {
        this.write("           - BRAVO -", true);
        this.write("Vous avez gagné apres : " + attempCounter + " essai(s)", true);
    }

    public void printTitle() {
        this.write(
                """
                                             _                           _             _\s
                          /\\/\\    __ _  ___ | |_   ___  _ __  _ __ ___  (_) _ __    __| |
                         /    \\  / _` |/ __|| __| / _ \\| '__|| '_ ` _ \\ | || '_ \\  / _` |
                        / /\\/\\ \\| (_| |\\__ \\| |_ |  __/| |   | | | | | || || | | || (_| |
                        \\/    \\/ \\__,_||___/ \\__| \\___||_|   |_| |_| |_||_||_| |_| \\__,_|
                        """, true);
    }

    public void printRules(int digitNumber) {
        this.write("1 - Le jeu retournera un signe + pour un match exact", true);
        this.write("2 - Le jeu retournera un signe - pour un match partiel", true);
        this.write("3 - Un match exact est un digit à la bonne valeur et la bonne position par rapport au code secret", true);
        this.write("4 - Un match partiel est un digit à la bonne valeur mais à la mauvaise position par rapport au code secret", true);
        this.write("5 - Les matchs exacts sont prioritaires par rapport aux matchs partiels", true);
        this.write("6 - Une fois qu 'un digit a été utilisé pour un match, il ne peut plus être utilisé pour un autre match", true);
        this.write("Entrez un code a " + digitNumber + " chiffres pour commencer", true);
    }

    public void printStartTurn(int attemptCounter) {
        this.write("Tour n°" + attemptCounter + ":", false);
    }

    public void printDigits(Set<Digit> inputedDigits) {
        inputedDigits.forEach(digit -> this.write("position : " + digit.getPosition() + " value :" + digit.getValue(), true));
    }

    public void printError(Exception e) throws IOException {
        this.writer.write(e.getLocalizedMessage());
        this.writer.newLine();
        this.writer.flush();
    }

    public void write(String messageToWrite, boolean newLine) {
        try {
            this.writer.write(messageToWrite);
            if (newLine) {
                this.writer.newLine();
            }
            this.writer.flush();

        } catch (IOException e) {
            throw new PrintingException("Une erreur est survenue lors de l'écriture", e);
        }
    }

    public void printMatchingError(String inputedCode, String matching) {
        this.write("Proposition : " + inputedCode + "  Réponse : " + matching, true);
    }
}
