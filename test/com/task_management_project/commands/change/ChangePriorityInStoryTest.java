package com.task_management_project.commands.change;

import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.Size;
import com.task_management_project.models.enums.StoryStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangePriorityInStoryTest {
    private TaskManagementRepository taskManagementRepository;
    private ChangePriorityInStory changePriorityInStory;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        changePriorityInStory = new ChangePriorityInStory(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> parameters = Arrays.asList("1");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changePriorityInStory.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_TooManyArguments() {
        // Arrange
        List<String> parameters = Arrays.asList("1", "HIGH", "Extra Argument");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changePriorityInStory.execute(parameters));
    }

    @Test
    public void executeCommand_ChangesPrioritySuccessfully() {
        // Create person and story
        Person person = taskManagementRepository.createPerson("John Doe");
        Story story = taskManagementRepository.createStory("StoryTitle1", "StoryDescription", Priority.LOW, StoryStatus.INPROGRESS, Size.LARGE, person);

        // Add story to repository
        taskManagementRepository.getStories().add(story);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(story.getId()), "HIGH");

        // Act
        String result = changePriorityInStory.executeCommand(parameters);

        // Assert
        assertEquals("Story priority on StoryTitle1 was changed to High", result);
        assertEquals(Priority.HIGH, taskManagementRepository.getStories().get(0).getPriority());
    }

    @Test
    public void executeCommand_ThrowsException_WhenStoryNotFound() {
        // Arrange
        List<String> parameters = Arrays.asList("999", "HIGH");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changePriorityInStory.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenPriorityIsInvalid() {
        // Create person and story
        Person person = taskManagementRepository.createPerson("John Doe");
        Story story = taskManagementRepository.createStory("StoryTitle1", "StoryDescription", Priority.LOW, StoryStatus.INPROGRESS, Size.LARGE, person);

        // Add story to repository
        taskManagementRepository.getStories().add(story);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(story.getId()), "INVALID_PRIORITY");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changePriorityInStory.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenStoryIdIsInvalid() {
        // Arrange
        List<String> parameters = Arrays.asList("invalid_id", "HIGH");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changePriorityInStory.executeCommand(parameters));
    }
}
