package bareum.commands;

import bareum.BareumException;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws BareumException {
        String deletedTask = taskList.get(index).toString();
        taskList.delete(index);
        storage.saveAllTasks(taskList);
        // exception for if index doesn't exist
        Ui.reply("Okay, I've deleted this task from the list:\n" + deletedTask
                + "\nYou now have " + taskList.size() + " tasks in your list.");
    }
}