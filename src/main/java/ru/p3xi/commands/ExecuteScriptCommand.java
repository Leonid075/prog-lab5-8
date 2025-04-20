package ru.p3xi.commands;

import java.io.BufferedInputStream;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import ru.p3xi.cm.Model;
import ru.p3xi.console.VirtualConsole;

public class ExecuteScriptCommand extends Command {
    public ExecuteScriptCommand() {
        super("execute_script", "Исполнить скрипт из файла", new Object[] {new String()}, "filename");
    }

    @Override
    public Object[] fillArgs(VirtualConsole con) {
        String input = con.readLine("Введите название файла:");
        return new Object[] {input};
    }

    @Override
    public void execute(Model model, Object[] args) throws ArgsException {
        String filename;
        try {
            filename = (String) args[0];
        } catch (Exception e) {
            throw new ArgsException("Неверные аргументы команды " + getName());
        }
        FileInputStream obj;
        String fileContent;
        try {
            obj = new FileInputStream(filename);
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
            return;
        }
        try {
            fileContent = new String(new BufferedInputStream(obj).readAllBytes());
            System.out.println(fileContent);
        }
        catch (IOException e) {
            System.out.println(e);
            return;
        }
    }
}
