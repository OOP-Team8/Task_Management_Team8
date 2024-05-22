package com.task_management_project.commands.change;

import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.enums.BugSeverity;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.models.enums.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeStatusInBugTest {
    private TaskManagementRepository taskManagementRepository;
    private ChangeStatusInBug changeStatusInBug;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        changeStatusInBug = new ChangeStatusInBug(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> parameters = Arrays.asList("1");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInBug.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_TooManyArguments() {
        // Arrange
        List<String> parameters = Arrays.asList("1", "ACTIVE", "Extra Argument");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInBug.execute(parameters));
    }

    @Test
    public void executeCommand_ChangesStatusSuccessfully() {
        // Create person and bug
        Person person = taskManagementRepository.createPerson("John Doe");
        Bug bug = taskManagementRepository.createBug("BugTitle1111", "BugDescription", Priority.HIGH, BugStatus.ACTIVE, BugSeverity.MINOR, person);

        // Add bug to repository
        taskManagementRepository.getBugs().add(bug);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(bug.getId()), "DONE");

        // Act
        String result = changeStatusInBug.executeCommand(parameters);

        // Assert
        assertEquals("Bug status on BugTitle1111 was changed to Done", result);
      //  assertEquals(BugStatus.DONE, bug.getStatus());
    }

    @Test
    public void executeCommand_ThrowsException_WhenBugNotFound() {
        // Arrange
        List<String> parameters = Arrays.asList("999", "DONE");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInBug.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenStatusIsInvalid() {
        // Create person and bug
        Person person = taskManagementRepository.createPerson("John Doe");
        Bug bug = taskManagementRepository.createBug("BugTitle1111", "BugDescription", Priority.HIGH, BugStatus.ACTIVE, BugSeverity.MINOR, person);

        // Add bug to repository
        taskManagementRepository.getBugs().add(bug);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(bug.getId()), "INVALID_STATUS");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInBug.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenBugIdIsInvalid() {
        // Arrange
        List<String> parameters = Arrays.asList("invalid_id", "DONE");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInBug.executeCommand(parameters));
    }
}
