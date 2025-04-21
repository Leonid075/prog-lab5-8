package ru.p3xi.console;

public abstract class VirtualConsole {
    public abstract String readLine() throws FileEndException;

    public abstract String readLine(String input) throws FileEndException;

    public abstract void writeLine(String input);
}
