package ru.p3xi.console;

import java.util.Arrays;
import java.util.Iterator;

public class VirtualFileConsole extends VirtualConsole {
    private Iterator<String> lines;

    public VirtualFileConsole(String lines) {
        this.lines = Arrays.asList(lines.split("\n")).iterator();
    }

    @Override
    public String readLine() throws FileEndException {
        if (!lines.hasNext()) throw new FileEndException("FileEndException");
        return lines.next();
    }

    @Override
    public String readLine(String input) throws FileEndException {
        return readLine();
    }
    @Override
    public void writeLine(String input) {
        return;
    }
}
