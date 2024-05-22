package com.task_management_project.commands.change;

import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Feedback;
import com.task_management_project.models.enums.FeedbackStatus;
import com.task_management_project.models.contracts.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeStatusInFeedbackTest {
    private TaskManagementRepository taskManagementRepository;
    private ChangeStatusInFeedback changeStatusInFeedback;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        changeStatusInFeedback = new ChangeStatusInFeedback(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> parameters = Arrays.asList("1");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInFeedback.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_TooManyArguments() {
        // Arrange
        List<String> parameters = Arrays.asList("1", "NEW", "Extra Argument");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInFeedback.execute(parameters));
    }

    @Test
    public void executeCommand_ChangesStatusSuccessfully() {
        // Create person
        Person person = taskManagementRepository.createPerson("John Doe");

        // Create feedback
        Feedback feedback = taskManagementRepository.createFeedback("FeedbackTitle1", "FeedbackDescription", FeedbackStatus.NEW, 1, person);

        // Add feedback to repository
        taskManagementRepository.getFeedbacks().add(feedback);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(feedback.getId()), "DONE");

        // Act
        String result = changeStatusInFeedback.executeCommand(parameters);

        // Assert
        assertEquals("Feedback status on FeedbackTitle1 was changed to Done", result);
      //  assertEquals(FeedbackStatus.DONE, feedback.getStatus());
    }

    @Test
    public void executeCommand_ThrowsException_WhenFeedbackNotFound() {
        // Arrange
        List<String> parameters = Arrays.asList("999", "DONE");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInFeedback.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenStatusIsInvalid() {
        // Create person
        Person person = taskManagementRepository.createPerson("John Doe");

        // Create feedback
        Feedback feedback = taskManagementRepository.createFeedback("FeedbackTitle1", "FeedbackDescription", FeedbackStatus.NEW, 1, person);

        // Add feedback to repository
        taskManagementRepository.getFeedbacks().add(feedback);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(feedback.getId()), "INVALID_STATUS");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInFeedback.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenFeedbackIdIsInvalid() {
        // Arrange
        List<String> parameters = Arrays.asList("invalid_id", "DONE");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInFeedback.executeCommand(parameters));
    }
}
