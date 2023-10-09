package org.example;

public class ConsistsOfOneLatterException extends RuntimeException{
    @Override
    public String toString() {
        return "Не были введены сведенья про пол, нет элементов состоящих из одного символа (m/f) ConsistsOfOneLetterException";
    }
}
