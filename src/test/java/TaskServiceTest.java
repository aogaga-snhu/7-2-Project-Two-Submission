// CS 320 Project One — TaskServiceTest
// Implements or verifies the corresponding in-memory service requirements.

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskServiceTest {

    private TaskService service;
    private Task task;

    @BeforeEach
    void setUp() {
        service = new TaskService();

        task = new Task(
                "TASK001",
                "Unit Testing",
                "Create and run JUnit tests.");
    }

    @Test
    void testAddTask() {
        service.addTask(task);

        assertSame(task, service.getTask("TASK001"));
    }

    @Test
    void testAddMultipleTasksWithUniqueIds() {
        Task secondTask = new Task(
                "TASK002",
                "Code Review",
                "Review the completed source code.");

        service.addTask(task);
        service.addTask(secondTask);

        assertSame(task, service.getTask("TASK001"));
        assertSame(secondTask, service.getTask("TASK002"));
    }

    @Test
    void testAddDuplicateTaskId() {
        service.addTask(task);

        Task duplicate = new Task(
                "TASK001",
                "Different Task",
                "This task uses a duplicate ID.");

        assertThrows(IllegalArgumentException.class,
                () -> service.addTask(duplicate));
        assertSame(task, service.getTask("TASK001"));
    }

    @Test
    void testAddNullTask() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addTask(null));
    }

    @Test
    void testDeleteTask() {
        service.addTask(task);

        service.deleteTask("TASK001");

        assertThrows(IllegalArgumentException.class,
                () -> service.getTask("TASK001"));
    }

    @Test
    void testDeleteTaskThatDoesNotExist() {
        assertThrows(IllegalArgumentException.class,
                () -> service.deleteTask("TASK999"));
    }

    @Test
    void testUpdateName() {
        service.addTask(task);

        service.updateName("TASK001", "Updated Task");

        assertEquals(
                "Updated Task",
                service.getTask("TASK001").getName());
    }

    @Test
    void testUpdateDescription() {
        service.addTask(task);

        service.updateDescription(
                "TASK001",
                "Updated task description");

        assertEquals(
                "Updated task description",
                service.getTask("TASK001").getDescription());
    }

    @Test
    void testUpdateTaskThatDoesNotExist() {
        assertThrows(IllegalArgumentException.class,
                () -> service.updateName(
                        "TASK999",
                        "Updated Task"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateDescription(
                        "TASK999",
                        "Updated description"));
    }

    @Test
    void testUpdateWithInvalidName() {
        service.addTask(task);

        assertThrows(IllegalArgumentException.class,
                () -> service.updateName(
                        "TASK001",
                        "123456789012345678901"));
    }

    @Test
    void testUpdateWithNullName() {
        service.addTask(task);

        assertThrows(IllegalArgumentException.class,
                () -> service.updateName("TASK001", null));
    }

    @Test
    void testUpdateWithInvalidDescription() {
        service.addTask(task);

        assertThrows(IllegalArgumentException.class,
                () -> service.updateDescription(
                        "TASK001",
                        "123456789012345678901234567890123456789012345678901"));
    }

    @Test
    void testUpdateWithNullDescription() {
        service.addTask(task);

        assertThrows(IllegalArgumentException.class,
                () -> service.updateDescription("TASK001", null));
    }

    @Test
    void testFailedUpdateDoesNotChangeExistingName() {
        service.addTask(task);

        assertThrows(IllegalArgumentException.class,
                () -> service.updateName(
                        "TASK001",
                        "123456789012345678901"));

        assertEquals(
                "Unit Testing",
                service.getTask("TASK001").getName());
    }

    @Test
    void testFailedUpdateDoesNotChangeExistingDescription() {
        service.addTask(task);

        assertThrows(IllegalArgumentException.class,
                () -> service.updateDescription(
                        "TASK001",
                        "123456789012345678901234567890123456789012345678901"));

        assertEquals(
                "Create and run JUnit tests.",
                service.getTask("TASK001").getDescription());
    }
}
