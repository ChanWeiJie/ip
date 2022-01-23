package duke.command;

import duke.functionality.TaskList;

public class MarkCommand extends Command {

    public MarkCommand(Integer number) {
        super(null, number,null);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.markTask(super.index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
