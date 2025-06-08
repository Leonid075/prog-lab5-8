package ru.p3xi.console;

import java.io.Console;

/**
 * Класс виртуальной реальной консоли- оболочка над консолью
 */
public class VirtualRealConsole extends VirtualConsole {
    Console con;

    public VirtualRealConsole() {
        con = System.console();
    }

    @Override
    public String readLine() {
        return con.readLine();
    }

    @Override
    public String readLine(String input) {
        return con.readLine(input);
    }

    @Override
    public void writeLine(String input) {
        System.out.println(input);
    }
}
