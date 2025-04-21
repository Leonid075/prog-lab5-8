package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.*;

public class RemoveByDiffCommand extends Command {
    public RemoveByDiffCommand() {
        super("remove_all_by_difficulty",
                "Удалить из коллекции все элементы, значение difficulty которого эквивалентно заданному",
                new Object[] { Difficulty.EASY }, "difficulty");
    }

    @Override
    public Object[] fillArgs(VirtualConsole con) throws FileEndException {
        return new Object[] { };
    }

    @Override
    public void execute(Model model, Object[] args) throws ArgsException {
        Difficulty difficulty;
        try {
            difficulty = Difficulty.valueOf((String) args[0]);
        } catch (Exception e) {
            throw new ArgsException("Неверные аргументы команды " + getName());
        }
        try {
        model.removeByDiff(difficulty);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Удалены объекты со значеним difficulty "+difficulty.toString());
    }
}
