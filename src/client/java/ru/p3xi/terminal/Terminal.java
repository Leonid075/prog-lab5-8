package ru.p3xi.terminal;

import java.util.HashMap;

import ru.p3xi.cnet.ClientNet;
import ru.p3xi.console.*;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;
import ru.p3xi.ccommands.*;

/**
 * Класс терминала, для обработки пользовательского ввода
 */
public class Terminal {
    private String username;
    private String password;

    /** Словарь команд */
    HashMap<String, Command> commands = new HashMap<>();

    public Terminal(Command[] commands) {
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
    public void execute(ClientNet net, VirtualConsole con) {
        try {
            CommandRequest checkUserCommand = new CheckUserCommand().fillArgs(con, new String[] { "check_user" });
            CommandRequest registerCommand = new RegisterCommand().fillArgs(con, new String[] { "register" });
            while (true) {
                username = con.readLine("Введите имя пользователя: ");
                password = con.readLine("Введите пароль (если требуется): ");
                checkUserCommand.setUsername(username);
                checkUserCommand.setPassword(password);
                CommandResponce responce = net.SendRequest(checkUserCommand);
                System.out.println(responce.getResponce());
                if (responce.getIsOk()) {
                    break;
                } else {
                    if (responce.getResponce().equals("Неверный пароль")) {
                        return;
                    } else {
                        String ans = con.readLine("Пользователь не найден. Зарегистрировать? [д/Н]: ");
                        if (ans.equals("д") || ans.equals("да") || ans.equals("Д") || ans.equals("Да")
                                || ans.equals("ДА")) {
                            registerCommand.setUsername(username);
                            registerCommand.setPassword(password);
                            if (net.SendRequest(registerCommand).getIsOk()) {
                                break;
                            } else
                                return;
                        }
                    }
                }
            }
        } catch (FileEndException e) {
            return;
        }
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
                for (Command i : commands.values()) {
                    System.out.println(" " + i.getName() + " " + i.getArgsDescription() + " - " + i.getDescription());
                }
            }

            else if (args[0].equals("quit") || args[0].equals("exit") || args[0].equals("psg"))
                return;

            else {
                Command executed = commands.get(args[0]);
                if (executed == null) {
                    System.out.println("Команды " + args[0] + " нет");
                    System.out.println("Используйте help для получения списка доступных команд");
                }
                try {
                    CommandRequest request = executed.fillArgs(con, args);
                    request.setUsername(username);
                    request.setPassword(password);

                    executed.execute(net, request);
                } catch (FileEndException | ArgsException e) {
                    System.out.println(e);
                } catch (Exception e) {
                }
            }
        }
    }
}
