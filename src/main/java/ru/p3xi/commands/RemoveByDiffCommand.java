package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.*;

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
    public void execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getDifficulty() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        try {
            model.removeByDiff(args.getDifficulty());
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Удалены объекты со значеним difficulty " + args.getDifficulty().toString());
    }
}
