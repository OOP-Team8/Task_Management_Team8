package com.task_management_project.commands.assignAndUnassign;

import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.StoryStatus;
import com.task_management_project.models.enums.Size;
import com.task_management_project.models.contracts.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnassignTaskToPersonTest {
    private TaskManagementRepository taskManagementRepository;
    private UnassignTaskToPerson unassignTaskToPerson;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        unassignTaskToPerson = new UnassignTaskToPerson(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> parameters = Arrays.asList("1");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> unassignTaskToPerson.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_TooManyArguments() {
        // Arrange
        List<String> parameters = Arrays.asList("1", "John Doe", "Extra Argument");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> unassignTaskToPerson.execute(parameters));
    }

    @Test
    public void executeCommand_UnassignsTaskSuccessfully() {
        // Create person and task
        Person person = taskManagementRepository.createPerson("John Doe");
        Task task = taskManagementRepository.createStory("TaskTitle1", "TaskDescription", Priority.HIGH, StoryStatus.INPROGRESS, Size.LARGE, person);

        // Add person and task to repository
        taskManagementRepository.addPerson(person);
        taskManagementRepository.getTasks().add(task);
        taskManagementRepository.addTaskToMember(task,person);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(task.getId()), "John Doe");

        // Act
        String result = unassignTaskToPerson.executeCommand(parameters);

        // Assert
        assertEquals("John Doe completed his task with name - TaskTitle1", result);
    }

    @Test
    public void executeCommand_ThrowsException_WhenTaskNotFound() {
        // Arrange
        List<String> parameters = Arrays.asList("999", "John Doe");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> unassignTaskToPerson.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenPersonNotFound() {
        // Create person and task
        Person person = taskManagementRepository.createPerson("John Doe");
        Task task = taskManagementRepository.createStory("TaskTitle1", "TaskDescription", Priority.HIGH, StoryStatus.INPROGRESS, Size.LARGE, person);

        // Add person and task to repository
        taskManagementRepository.addPerson(person);
        taskManagementRepository.getTasks().add(task);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(task.getId()), "NonExistentPerson");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> unassignTaskToPerson.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenTaskIdIsInvalid() {
        // Arrange
        List<String> parameters = Arrays.asList("invalid_id", "John Doe");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> unassignTaskToPerson.executeCommand(parameters));
    }
}
