package ru.p3xi.ccommands;

import ru.p3xi.cnet.ClientNet;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.*;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

/**
 * Удалить элементы коллекции с заданной difficulty
 */
public class RemoveByDiffCommand extends Command {
    public RemoveByDiffCommand() {
        super("remove_all_by_difficulty",
                "Удалить из коллекции все элементы, значение difficulty которого эквивалентно заданному", "difficulty");
    }

    @Override
    public CommandRequest fillArgs(VirtualConsole con, String[] args) throws FileEndException {
        try {
            return new CommandRequest.Builder().command(args[0]).difficulty(Difficulty.valueOf(args[1])).build();
        } catch (IllegalArgumentException e) {
            System.out.println("Доступны занчения VERY_EASY, EASY, HOPELESS");
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void execute(ClientNet net, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getDifficulty() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        CommandResponce responce = net.SendRequest(args);
        System.out.println(responce.getResponce());
    }
}
