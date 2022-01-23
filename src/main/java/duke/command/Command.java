package duke.command;

import duke.task.Task;
import duke.functionality.TaskList;

public abstract class Command {
    protected Task task;
    protected Integer index;

    public Command(Task task, Integer number) {
        this.task = task;
        this.index = number;
    }

    public abstract void execute(TaskList tasks);

    public abstract boolean isExit();
}
