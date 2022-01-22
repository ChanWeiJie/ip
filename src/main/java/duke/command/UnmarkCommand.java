package duke.command;

import duke.TaskList;

public class UnmarkCommand extends Command{

    public UnmarkCommand(Integer number) {
        super(null, number);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.unMarkTask(super.index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}