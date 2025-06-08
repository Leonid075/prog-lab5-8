package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
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
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getDifficulty() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        try {
            model.removeByDiff(args.getDifficulty());
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommandResponce.Builder().isOk(true).responce("Удалены объекты со значеним difficulty " + args.getDifficulty().toString()).build();
    }
}
