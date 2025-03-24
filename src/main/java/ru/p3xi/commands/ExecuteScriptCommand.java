package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;

public class ExecuteScriptCommand extends Command {
    public ExecuteScriptCommand() {
        super("execute_script", "Исполнить скрипт из файла", new Object[] {new String()}, "filename");
    }

    @Override
    public Object[] fillArgs(Console con) {
        String input = con.readLine("Введите название файла:");
        return new Object[] {input};
    }

    @Override
    public void execute(Model model, Object[] args) {
        
    }
}
