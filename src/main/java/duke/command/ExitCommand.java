package duke.command;

import duke.functionality.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(null, null);
    }

    @Override
    public void execute(TaskList tasks) { }

    @Override
    public boolean isExit() {
        return true;
    }
}
