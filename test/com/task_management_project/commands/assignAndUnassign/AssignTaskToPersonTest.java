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

public class AssignTaskToPersonTest {
    private TaskManagementRepository taskManagementRepository;
    private AssignTaskToPerson assignTaskToPerson;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        assignTaskToPerson = new AssignTaskToPerson(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> parameters = Arrays.asList("1");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> assignTaskToPerson.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_TooManyArguments() {
        // Arrange
        List<String> parameters = Arrays.asList("1", "John Doe", "Extra Argument");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> assignTaskToPerson.execute(parameters));
    }

//    @Test
//    public void executeCommand_AssignsTaskSuccessfully() {
//        // Create person, another person, and task
//        Person person1 = taskManagementRepository.createPerson("John Doe");
//        Person person2 = taskManagementRepository.createPerson("Jane Smith");
//        Task task = taskManagementRepository.createStory("TaskTitle1", "TaskDescription", Priority.HIGH, StoryStatus.INPROGRESS, Size.LARGE, person1);
//
//        // Add person and task to repository
//        taskManagementRepository.addPerson(person1);
//        taskManagementRepository.addPerson(person2);
//        taskManagementRepository.getTasks().add(task);
//
//        // Arrange
//        List<String> parameters = Arrays.asList(String.valueOf(task.getId()), person2.getName());
//
//        // Act
//        String result = assignTaskToPerson.executeCommand(parameters);
//
//        // Assert
//        assertEquals("John Doe gives a new task with name - TaskTitle1 to Jane Smith", result);
//        assertEquals(person2.getName(), task.getPerson().getName());
//    }

    @Test
    public void executeCommand_AssignsTaskToSamePersonSuccessfully() {
        // Create person and task
        Person person = taskManagementRepository.createPerson("John Doe");
        Task task = taskManagementRepository.createStory("TaskTitle1", "TaskDescription", Priority.HIGH, StoryStatus.INPROGRESS, Size.LARGE, person);

        // Add person and task to repository
        taskManagementRepository.addPerson(person);
        taskManagementRepository.getTasks().add(task);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(task.getId()), "John Doe");

        // Act
        String result = assignTaskToPerson.executeCommand(parameters);

        // Assert
        assertEquals("John Doe takes a new task with name - TaskTitle1", result);
        assertEquals(person.getName(), task.getPerson().getName());
    }
    @Test
    public void executeCommand_ThrowsException_WhenTaskNotFound() {
        // Arrange
        List<String> parameters = Arrays.asList("999", "John Doe");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> assignTaskToPerson.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenPersonNotFound() {
        // Create person and task
        Person person = taskManagementRepository.createPerson("John Doe");
        Task task = taskManagementRepository.createStory("TaskTitle1", "TaskDescription", Priority.HIGH, StoryStatus.INPROGRESS, Size.LARGE, person);

        // Add task to repository
        taskManagementRepository.getTasks().add(task);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(task.getId()), "NonExistentPerson");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> assignTaskToPerson.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenTaskIdIsInvalid() {
        // Arrange
        List<String> parameters = Arrays.asList("invalid_id", "John Doe");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> assignTaskToPerson.executeCommand(parameters));
    }
}