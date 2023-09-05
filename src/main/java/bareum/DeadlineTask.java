package bareum;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    LocalDate dueDate;

    private DeadlineTask(boolean isDone, String description, LocalDate dueDate) {
        super(isDone, description);
        this.dueDate = dueDate;
        this.isDone = false;
        // add exceptions for time
    }

    public static DeadlineTask makeDeadline(String description, String dueDateStr) throws BareumException {
        LocalDate dueDate = null;
        try {
            dueDate = LocalDate.parse(dueDateStr);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            throw new BareumException("Oops! Please enter the due date in YYYY-MM-DD :(\n" +
                    "Correct format: deadline <description> /by <due date in YYYY-MM-DD>");
        }
        return new DeadlineTask(false, description, dueDate);
    }

    public static DeadlineTask makeDeadline(String[] taskInputs) {
        String description = taskInputs[2];
        String dueDateStr = taskInputs[3];
        LocalDate dueDate = null;
        dueDate = LocalDate.parse(dueDateStr);
        boolean isDone = taskInputs[1].equals("1");
        return new DeadlineTask(isDone, description, dueDate);
    }

    @Override
    public String toString() {
        String dueDate = this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D][" + this.getStatusIcon() + "] " + this.description
                + "(by: " + dueDate + ")";
    }

    @Override
    public String toSavedString() {
        String done = isDone ? "1" : "0";
        return "D|" + done + "|" + this.description + "|" + dueDate +"\n";
    }
}