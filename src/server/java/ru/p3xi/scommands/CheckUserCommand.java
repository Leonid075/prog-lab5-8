package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

/**
 * Проверить, существует ли пользователь
 */
public class CheckUserCommand extends Command {
    public CheckUserCommand() {
        super("check_user", "Проверить, существует ли пользователь", "{username}");
    }

    @Override
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        boolean exists = model.userExist(args.getUsername());
        if (exists) {
            if(model.comparePassword(args.getUsername(), args.getPassword())) {
                return new CommandResponce.Builder().isOk(true).responce("Пользователь существует").build();
            } else {
                return new CommandResponce.Builder().isOk(false).responce("Неверный пароль").build();
            }
        }
        else
            return new CommandResponce.Builder().isOk(false).responce("Пользователь не существует").build();
    }
}
