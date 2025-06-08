package ru.p3xi.console;

/**
 * Абстактный класс виртуальной консоли
 */
public abstract class VirtualConsole {
    /**
     * Читать строку из консоли
     */
    public abstract String readLine() throws FileEndException;

    /**
     * Читать строку из консоли, с выводом
     */
    public abstract String readLine(String input) throws FileEndException;

    /**
     * Вывести строку в консоль
     */
    public abstract void writeLine(String input);
}
