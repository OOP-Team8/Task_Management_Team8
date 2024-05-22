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

public class ChangeSeverityInBugTest {
    private TaskManagementRepository taskManagementRepository;
    private ChangeSeverityInBug changeSeverityInBug;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        changeSeverityInBug = new ChangeSeverityInBug(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> parameters = Arrays.asList("1");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeSeverityInBug.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_TooManyArguments() {
        // Arrange
        List<String> parameters = Arrays.asList("1", "CRITICAL", "Extra Argument");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeSeverityInBug.execute(parameters));
    }

    @Test
    public void executeCommand_ChangesSeveritySuccessfully() {
        // Create person and bug
        Person person = taskManagementRepository.createPerson("John Doe");
        Bug bug = taskManagementRepository.createBug("BugTitle111", "BugDescription", Priority.HIGH, BugStatus.ACTIVE, BugSeverity.MINOR, person);

        // Add bug to repository
        taskManagementRepository.getBugs().add(bug);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(bug.getId()), "CRITICAL");

        // Act
        String result = changeSeverityInBug.executeCommand(parameters);

        // Assert
        assertEquals("Bug severity on BugTitle111 was changed to Critical", result);
        //  assertEquals(Size.LARGE, taskManagementRepository.getBugs().get(0).getBugStatus();
    }

    @Test
    public void executeCommand_ThrowsException_WhenBugNotFound() {
        // Arrange
        List<String> parameters = Arrays.asList("999", "CRITICAL");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeSeverityInBug.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenSeverityIsInvalid() {
        // Create person and bug
        Person person = taskManagementRepository.createPerson("John Doe");
        Bug bug = taskManagementRepository.createBug("BugTitle111", "BugDescription", Priority.HIGH, BugStatus.ACTIVE, BugSeverity.MINOR, person);

        // Add bug to repository
        taskManagementRepository.getBugs().add(bug);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(bug.getId()), "INVALID_SEVERITY");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeSeverityInBug.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenBugIdIsInvalid() {
        // Arrange
        List<String> parameters = Arrays.asList("invalid_id", "CRITICAL");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeSeverityInBug.executeCommand(parameters));
    }
}
