package org.example;

public class ConsistsOfLettersException extends NullPointerException{
    @Override
    public String toString() {
        return "Нет ни одного введенного слова, введен не верный символ ConsistsOfLettersException";
    }
}
