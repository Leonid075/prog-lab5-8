package ru.p3xi.labwork;

/**
 * Исключение- неправильные данные для заполнения
 */
public class ValueException extends Exception {
    public ValueException(String errorMessage) {
        super(errorMessage);
    }
}
