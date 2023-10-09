package org.example;

public class ConsistsOfNumbersException extends RuntimeException{
    @Override
    public String toString() {
        return "Не был введен номер телефона, нет ни одного элемента состоящего только из цифр, ошибка ConsistsOfNumbersException";
    }
}
