package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;
import ru.p3xi.labwork.LabWorkBuilder;

public class UpdateCommand extends Command {
    public UpdateCommand() {
        super("update", "Обновить элемент в коллекции по id", new Object[] { new Long(1l), new LabWork() },
                "id, {element}");
    }

    @Override
    public Object[] fillArgs(VirtualConsole con) throws FileEndException {
        LabWorkBuilder labWorkBuilder = new LabWorkBuilder();
        labWorkBuilder.buildInTerminal(con);
        try {
            return new Object[] { new LabWork(labWorkBuilder) };
        } catch (Exception e) {
            return new Object[] { labWorkBuilder };
        }
    }

    @Override
    public void execute(Model model, Object[] args) throws ArgsException {
        long id;
        LabWork labWork;
        try {
            id = new Long((String) args[0]).longValue();
            labWork = (LabWork) args[1];
        } catch (Exception e) {
            throw new ArgsException("Неверные аргументы команды " + getName());
        }
        model.update(id, labWork);
        System.out.println("Обновлено значение объекта с id "+id);
    }
}
