// CS 320 Project One — Task
// Implements or verifies the corresponding in-memory service requirements.

public class Task {

    private final String taskId;
    private String name;
    private String description;

    public Task(String taskId, String name, String description) {
        validateTaskId(taskId);
        validateName(name);
        validateDescription(description);

        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    private static void validateTaskId(String taskId) {
        if (taskId == null || taskId.length() > 10) {
            throw new IllegalArgumentException(
                    "Task ID must not be null or longer than 10 characters.");
        }
    }

    private static void validateName(String name) {
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException(
                    "Task name must not be null or longer than 20 characters.");
        }
    }

    private static void validateDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException(
                    "Task description must not be null or longer than 50 characters.");
        }
    }
}
