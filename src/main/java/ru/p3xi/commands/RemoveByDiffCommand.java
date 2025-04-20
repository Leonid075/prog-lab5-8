package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.*;

public class RemoveByDiffCommand extends Command {
    public RemoveByDiffCommand() {
        super("remove_all_by_difficulty",
                "Удалить из коллекции все элементы, значение difficulty которого эквивалентно заданному",
                new Object[] { Difficulty.EASY }, "difficulty");
    }

    @Override
    public Object[] fillArgs(VirtualConsole con) {
        Difficulty difficulty;
        while (true) {
            try {
                String input = con.readLine("Введите значение difficulty: ");
                if (input.equals(""))
                    input = null;
                difficulty = Difficulty.valueOf(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
        }
        return new Object[] { difficulty };
    }

    @Override
    public void execute(Model model, Object[] args) throws ArgsException {
        Difficulty difficulty;
        try {
            difficulty = (Difficulty) args[0];
        } catch (Exception e) {
            throw new ArgsException("Неверные аргументы команды " + getName());
        }
        model.removeByDiff(difficulty);
        System.out.println("Удалены объекты со значеним difficulty "+difficulty.toString());
    }
}
