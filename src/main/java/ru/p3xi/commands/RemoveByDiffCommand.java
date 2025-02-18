package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.*;

public class RemoveByDiffCommand extends Command {
    public RemoveByDiffCommand() {
        super("remove_all_by_difficulty", "Удалить из коллекции все элементы, значение difficulty которого эквивалентно заданному", "difficulty");
    }

    @Override
    public void execute(Model model, Object[] args) {
        Difficulty difficulty = (Difficulty) args[0];
        model.removeByDiff(difficulty);
    }
}
