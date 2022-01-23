package duke.functionality;

import java.util.ArrayList;

import duke.task.Task;
/**
 * Represents the Tasks of the Duke project. A <code> TaskList </code> object corresponds
 * to the actions available on a Task object.
 */
public class TaskList {
    protected static ArrayList<Task> taskList = new ArrayList<>();
    protected static int numOfTask = 0;

    /**
     * Default constructor of TaskList class.
     */
    public TaskList() { }

    /**
     * Returns nothing, but deletes the task specified.
     * @param taskNum an indicator to the index of taskList.
     */
    public void deleteTask(int taskNum) {
        String message = "Noted. I've removed this task:\n";
        int actualTaskNum = taskNum - 1;
        System.out.println(message + taskList.get(actualTaskNum).toString());
        taskList.remove(actualTaskNum);
        numOfTask--;
        Storage.updateTextFile();
        System.out.println("Now you have " + numOfTask + " tasks in the list.");
    }

    /**
     * Returns nothing, but marks the task specified as done.
     * @param taskNum an indicator to the index of taskList.
     */
    public void markTask(int taskNum) {
        String message = "Nice! I've marked this task as done:\n" ;
        int actualTaskNum = taskNum - 1; //minus 1 as list index is from 0
        Task t = taskList.get(actualTaskNum); // get the task from the array
        t.setTaskDone();
        Storage.updateTextFile();
        System.out.println(message + t.toString());
    }

    /**
     * Returns nothing, but marks the task specified as not done.
     * @param taskNum an indicator to the index of taskList.
     */
    public void unMarkTask(int taskNum) {
        String message = "OK, I've marked this task as not done yet:\n";
        int actualTaskNum = taskNum - 1;
        Task t = taskList.get(actualTaskNum); // get the task from the array
        t.setTaskNotDone();
        Storage.updateTextFile();
        System.out.println(message + t.toString());
    }

    /**
     * Returns nothing, but prints out all task in taskList.
     */
    public void printList(){
        String message = "Here are the tasks in your list:";
        System.out.println(message);

        for(int i = 0; i < numOfTask; i++){
            String output = i + 1 + "." + taskList.get(i).toString();
            System.out.println(output);
        }
    }

    /**
     * Returns nothing, but adds the task specified into the taskList.
     * @param t the task created in Parser class.
     */
    public void addToList(Task t) {
        String message = "Got it. I've added this task:\n";
        taskList.add(t);
        numOfTask++;
        Storage.updateTextFile();
        System.out.println(message + t.toString() + "\nNow you have " + numOfTask + " tasks in the list.");
    }
}
