package main.java.fr.teemo.mastermind.service;

import main.java.fr.teemo.mastermind.exception.ReadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputHandler {


    private final BufferedReader input;


    public InputHandler(InputStream inputStream) {
        this.input = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String read() {
        try {
            return this.input.readLine();
        } catch (IOException e) {
            throw new ReadingException("Une erreur est survenue lors de la lecture de l'input utilisateur", e);
        }
    }
}
