package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.LabWork;
import ru.p3xi.labwork.LabWorkBuilder;

public class RemoveByIdCommand extends Command {
    public RemoveByIdCommand() {
        super("remove_by_id", "Удалить элемент коллекции по id", new Object[] { new Long(1l) }, "id");
    }

    @Override
    public Object[] fillArgs(Console con) {
        Long id;
        while (true) {
            try {
                String input = con.readLine("| | lectureHours: ");
                if (input.equals(""))
                    id = 0l;
                else
                    id = new Long(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        return new Object[] { id };
    }

    @Override
    public void execute(Model model, Object[] args) throws ArgsException {
        long id;
        try {
            id = (long) args[0];
        } catch (Exception e) {
            throw new ArgsException("Неверные аргументы команды " + getName());
        }
        model.remove(id);
        System.out.println("Удален объект со значеним id "+id+" (если он существовал)");
    }
}
