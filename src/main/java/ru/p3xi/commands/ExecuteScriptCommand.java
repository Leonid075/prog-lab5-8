package ru.p3xi.commands;

import ru.p3xi.cm.Model;

public class ExecuteScriptCommand extends Command {
    public ExecuteScriptCommand() {
        super("execute_script", "Исполнить скрипт из файла", "file_name");
    }

    @Override
    public void execute(Model model, Object[] args) {
        
    }
}
