package com.task_management_project.commands.change;

import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.models.enums.BugSeverity;
import com.task_management_project.models.contracts.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangePriorityInBugTest {
    private TaskManagementRepository taskManagementRepository;
    private ChangePriorityInBug changePriorityInBug;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        changePriorityInBug = new ChangePriorityInBug(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> parameters = Arrays.asList("1");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changePriorityInBug.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_TooManyArguments() {
        // Arrange
        List<String> parameters = Arrays.asList("1", "HIGH", "Extra Argument");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changePriorityInBug.execute(parameters));
    }

    @Test
    public void executeCommand_ChangesPrioritySuccessfully() {
        // Create person and bug
        Person person = taskManagementRepository.createPerson("John Doe");
        Bug bug = taskManagementRepository.createBug("BugTitle111", "BugDescription", Priority.LOW, BugStatus.ACTIVE, BugSeverity.MINOR, person);

        // Add bug to repository
        taskManagementRepository.getBugs().add(bug);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(bug.getId()),"HIGH");

        // Act
        String result = changePriorityInBug.executeCommand(parameters);

        // Assert
        assertEquals("Bug priority on BugTitle111 was changed to High", result);
        assertEquals(Priority.HIGH, taskManagementRepository.getBugs().get(0).getPriority());
    }

    @Test
    public void executeCommand_ThrowsException_WhenBugNotFound() {
        // Arrange
        List<String> parameters = Arrays.asList("999", "HIGH");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changePriorityInBug.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenPriorityInvalid() {
        // Create person and bug
        Person person = taskManagementRepository.createPerson("John Doe");
        Bug bug = taskManagementRepository.createBug("BugTitle111", "BugDescription", Priority.LOW, BugStatus.ACTIVE, BugSeverity.MINOR, person);

        // Add bug to repository
        taskManagementRepository.getBugs().add(bug);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(bug.getId()), "INVALID_PRIORITY");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changePriorityInBug.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenBugIdIsInvalid() {
        // Arrange
        List<String> parameters = Arrays.asList("invalid_id", "HIGH");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changePriorityInBug.executeCommand(parameters));
    }
}