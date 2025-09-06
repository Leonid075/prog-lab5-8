package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

public class RegisterCommand extends Command {
    public RegisterCommand() {
        super("register", "Зарегистрировать нового пользователя", "{username} {password}");
    }

    @Override
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        String username = args.getUsername();
        String password = args.getPassword();
        model.addUser(username, password);
        return new CommandResponce.Builder().isOk(true).responce("Пользователь зарегистрирован").build();
    }
}
