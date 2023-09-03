public abstract class Command {
    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws BareumException;
}