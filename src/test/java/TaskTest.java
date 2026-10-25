// CS 320 Project One — TaskTest
// Implements or verifies the corresponding in-memory service requirements.

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void testTaskCreation() {
        Task task = new Task(
                "TASK001",
                "Unit Testing",
                "Create and run JUnit tests.");

        assertEquals("TASK001", task.getTaskId());
        assertEquals("Unit Testing", task.getName());
        assertEquals("Create and run JUnit tests.", task.getDescription());
    }

    // Task ID requirements

    @Test
    void testTaskIdExactlyTenCharacters() {
        Task task = new Task(
                "1234567890",
                "Test Task",
                "Valid description");

        assertEquals("1234567890", task.getTaskId());
    }

    @Test
    void testTaskIdTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task(
                        "12345678901",
                        "Test Task",
                        "Valid description"));
    }

    @Test
    void testTaskIdNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task(
                        null,
                        "Test Task",
                        "Valid description"));
    }

    @Test
    void testTaskIdCannotBeUpdated() {
        Task task = createValidTask();

        assertThrows(NoSuchMethodException.class,
                () -> Task.class.getDeclaredMethod("setTaskId", String.class));

        task.setName("Updated Task");
        task.setDescription("Updated description");
        assertEquals("TASK001", task.getTaskId());
    }

    // Name requirements

    @Test
    void testNameExactlyTwentyCharacters() {
        String name = "12345678901234567890";

        Task task = new Task(
                "TASK001",
                name,
                "Valid description");

        assertEquals(name, task.getName());
    }

    @Test
    void testNameTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task(
                        "TASK001",
                        "123456789012345678901",
                        "Valid description"));
    }

    @Test
    void testNameNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task(
                        "TASK001",
                        null,
                        "Valid description"));
    }

    // Description requirements

    @Test
    void testDescriptionExactlyFiftyCharacters() {
        String description =
                "12345678901234567890123456789012345678901234567890";

        Task task = new Task(
                "TASK001",
                "Test Task",
                description);

        assertEquals(description, task.getDescription());
    }

    @Test
    void testDescriptionTooLong() {
        String description =
                "123456789012345678901234567890123456789012345678901";

        assertThrows(IllegalArgumentException.class, () ->
                new Task(
                        "TASK001",
                        "Test Task",
                        description));
    }

    @Test
    void testDescriptionNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task(
                        "TASK001",
                        "Test Task",
                        null));
    }

    // Update validation

    @Test
    void testUpdateName() {
        Task task = createValidTask();

        task.setName("Updated Task");

        assertEquals("Updated Task", task.getName());
    }

    @Test
    void testUpdateDescription() {
        Task task = createValidTask();

        task.setDescription("Updated task description");

        assertEquals("Updated task description", task.getDescription());
    }

    @Test
    void testInvalidNameUpdate() {
        Task task = createValidTask();

        assertThrows(IllegalArgumentException.class,
                () -> task.setName("123456789012345678901"));
    }

    @Test
    void testNullNameUpdate() {
        Task task = createValidTask();

        assertThrows(IllegalArgumentException.class,
                () -> task.setName(null));
    }

    @Test
    void testInvalidDescriptionUpdate() {
        Task task = createValidTask();

        assertThrows(IllegalArgumentException.class,
                () -> task.setDescription(
                        "123456789012345678901234567890123456789012345678901"));
    }

    @Test
    void testNullDescriptionUpdate() {
        Task task = createValidTask();

        assertThrows(IllegalArgumentException.class,
                () -> task.setDescription(null));
    }

    private Task createValidTask() {
        return new Task(
                "TASK001",
                "Test Task",
                "Valid task description");
    }
}
