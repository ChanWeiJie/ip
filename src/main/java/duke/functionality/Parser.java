package duke.functionality;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.PrintCommand;
import duke.command.UnmarkCommand;
import duke.command.ExitCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.exception.IncompleteCommandException;
import duke.exception.InvalidCommandException;

public class Parser {

    private static final int EVENT_OFFSET = 5;
    private static final int TODO_OFFSET = 4;
    private static final int DEADLINE_OFFSET = 8;
    private static final int INPUT_OFFSET = 3;

    public static LocalDate formatDate(String input){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "yyyy-MM-dd");
        LocalDate date = LocalDate.parse(input, dtf);
        return date;
    }

    public static LocalTime formatTime(String input) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "HHmm");
        LocalTime time = LocalTime.parse(input, dtf);
        return time;
    }

    public static String dateToString(LocalDate input) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "yyyy-MM-dd");
        String date = input.format(dtf);
        return date;
    }

    public static String timeToString(LocalTime input) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "HHmm");
        String time = input.format(dtf);
        return time;
    }

    public static Command parse(String input) throws DukeException {
        String[] inputSplit = input.split(" "); //split input by space
        String command = inputSplit[0];
        if (command.equals("bye")) {
            return new ExitCommand();

        } else if (command.equals("list")) {
            return new PrintCommand();

        } else if (command.equals("todo")) {
            String description = input.substring(TODO_OFFSET).trim();

            if (description.length() == 0) {
                throw new IncompleteCommandException(command);
            }
            return new AddCommand(new Todo(description));

        } else if (command.equals("deadline")) {
            String[] inputSlash = input.split("/");
            String description = inputSlash[0].substring(DEADLINE_OFFSET).trim();

            if (description.length() == 0) {
                throw new IncompleteCommandException(command);
            }

            String durationInput = inputSlash[1].substring(INPUT_OFFSET);
            String[] splitDuration = durationInput.split(" ");
            LocalDate date;
            LocalTime time;
            try{
                date = formatDate(splitDuration[0]);
                time = formatTime(splitDuration[1]);
            } catch(DateTimeParseException e) {
                throw new DukeException("The expected input form is deadline ____ /by yyyy-mm-dd hhmm");
            }

            return new AddCommand(new Deadline(description, date, time));

        } else if (command.equals("event")) {
            String[] inputSlash = input.split("/");
            String description = inputSlash[0].substring(EVENT_OFFSET).trim();

            if (description.length() == 0) {
                throw new IncompleteCommandException(command);
            }

            String durationInput = inputSlash[1].substring(INPUT_OFFSET);
            String[] splitDuration = durationInput.split(" ");
            LocalDate date;
            LocalTime startTime;
            LocalTime endTime;
            try{
                date = formatDate(splitDuration[0]);
                startTime = formatTime(splitDuration[1]);
                endTime = formatTime(splitDuration[2]);
            } catch(DateTimeParseException e) {
                throw new DukeException("The expected input form is event ____ /at yyyy-mm-dd hhmm hhmm");
            }

            return new AddCommand(new Event(description, date, startTime, endTime));

        } else if (command.equals("mark")) {
            return new MarkCommand(Integer.parseInt(inputSplit[1]));

        } else if (command.equals("unmark")) {
            return new UnmarkCommand(Integer.parseInt(inputSplit[1]));

        } else if (command.equals("delete")) {
            return new DeleteCommand(Integer.parseInt(inputSplit[1]));

        } else {
            throw new InvalidCommandException();
        }
    }
}
