package main.java.fr.teemo.mastermind.validator;

import main.java.fr.teemo.mastermind.exception.InvalidInputException;

import java.util.Arrays;

public class InputValidator {

    public void validate(String inputedString, Integer digitNumber, Integer[] acceptedValues) throws InvalidInputException {
        this.validateInputLength(inputedString, digitNumber);
        this.validateInputCharacters(inputedString, acceptedValues);
    }

    private void validateInputCharacters(String inputedString, Integer[] acceptedValues) throws InvalidInputException {
        if (inputedString.chars().anyMatch(digit -> Arrays.stream(acceptedValues).noneMatch(value -> Character.getNumericValue(digit) == value))) {
            throw new InvalidInputException("Le code que vous venez d'insérer contient des caractères non supportés, les seuls caractères acceptés sont les chiffres de 1 à 9");
        }
    }

    private void validateInputLength(String inputedString, int digitNumber) throws InvalidInputException {
        if (inputedString.length() != digitNumber) {
            throw new InvalidInputException("Le code que vous venez d'insérer n'a pas la bonne taille, la taille correcte est de " + digitNumber + " chiffres");
        }
    }

}
