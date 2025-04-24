package ru.p3xi.terminal;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import ru.p3xi.cm.Model;
import ru.p3xi.commands.*;
import ru.p3xi.console.*;

/**
 * Класс терминала, для обработки пользовательского ввода
 */
public class Terminal {
    /** Словарь команд */
    HashMap<String, Command> commands = new HashMap<>();

    public Terminal(Command... commands) {
        for (Command i : commands) {
            this.commands.put(i.getName(), i);
        }
    }

    /**
     * Запуск терминала
     * 
     * @param model
     * @param con
     */
    public void execute(Model model, VirtualConsole con) {
        while (true) {
            String[] args = new String[] {};
            try {
                args = con.readLine("> ").split(" ");
            } catch (FileEndException e) {
                return;
            }

            if (args.length == 0 || (args.length == 1 && args[0].equals("")))
                continue;

            if (args[0].equals("help")) {
                System.out.println("Доступные команды:");
                if (con.getClass().equals(VirtualRealConsole.class)) {
                    System.out.println(" execute_script file_name - Исполнить скрипт из файла");
                }
                for (Command i : commands.values()) {
                    System.out.println(" " + i.getName() + " " + i.getArgsDescription() + " - " + i.getDescription());
                }
            }

            else if (args[0].equals("quit") || args[0].equals("exit") || args[0].equals("psg"))
                return;

            else if (args[0].equals("execute_script")) {
                if (!con.getClass().equals(VirtualRealConsole.class)) {
                    System.out.println("execute_script нельзя использовать в скриптах");
                    continue;
                }
                String filename;
                try {
                    filename = (String) args[1];
                } catch (Exception e) {
                    System.out.println("Неверные аргументы команды execute_script");
                    continue;
                }
                FileInputStream obj;
                String fileContent;
                try {
                    obj = new FileInputStream(filename);
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                    return;
                }
                try {
                    fileContent = new String(new BufferedInputStream(obj).readAllBytes());
                    this.execute(model, new VirtualFileConsole(fileContent));
                } catch (IOException e) {
                    System.out.println(e);
                    return;
                }
            }

            else {
                Command executed = commands.get(args[0]);
                if (executed == null) {
                    System.out.println("Команды " + args[0] + " нет");
                    System.out.println("Используйте help для получения списка доступных команд");
                }
                try {
                    CommandRequest request = executed.fillArgs(con, args);
                    executed.execute(model, request);
                } catch (FileEndException | ArgsException e) {
                    System.out.println(e);
                } catch (Exception e) {
                }
            }
        }
    }
}
