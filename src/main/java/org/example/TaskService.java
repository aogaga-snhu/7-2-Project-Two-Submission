// CS 320 Project One — TaskService
// Implements or verifies the corresponding in-memory service requirements.

import java.util.HashMap;
import java.util.Map;

public class TaskService {

    private final Map<String, Task> tasks;

    public TaskService() {
        tasks = new HashMap<>();
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }

        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException(
                    "A task with this ID already exists.");
        }

        tasks.put(task.getTaskId(), task);
    }

    public void deleteTask(String taskId) {
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task ID not found.");
        }

        tasks.remove(taskId);
    }

    public void updateName(String taskId, String name) {
        getTask(taskId).setName(name);
    }

    public void updateDescription(String taskId, String description) {
        getTask(taskId).setDescription(description);
    }

    public Task getTask(String taskId) {
        Task task = tasks.get(taskId);

        if (task == null) {
            throw new IllegalArgumentException("Task ID not found.");
        }

        return task;
    }
}
