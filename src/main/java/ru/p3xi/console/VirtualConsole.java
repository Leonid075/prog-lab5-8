package ru.p3xi.console;

public abstract class VirtualConsole {
    public abstract String readLine();
    public abstract String readLine(String input);
    public abstract void writeLine(String input);
}
