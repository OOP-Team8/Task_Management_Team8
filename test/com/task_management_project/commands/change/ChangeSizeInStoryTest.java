package com.task_management_project.commands.change;

import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.StoryStatus;
import com.task_management_project.models.enums.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeSizeInStoryTest {
    private TaskManagementRepository taskManagementRepository;
    private ChangeSizeInStory changeSizeInStory;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        changeSizeInStory = new ChangeSizeInStory(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> parameters = Arrays.asList("1");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeSizeInStory.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_TooManyArguments() {
        // Arrange
        List<String> parameters = Arrays.asList("1", "LARGE", "Extra Argument");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeSizeInStory.execute(parameters));
    }

    @Test
    public void executeCommand_ChangesSizeSuccessfully() {
        // Create person and story
        Person person = taskManagementRepository.createPerson("John Doe");
        Story story = taskManagementRepository.createStory("StoryTitle1", "StoryDescription", Priority.LOW, StoryStatus.INPROGRESS, Size.MEDIUM, person);

        // Add story to repository
        taskManagementRepository.getStories().add(story);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(story.getId()), "LARGE");

        // Act
        String result = changeSizeInStory.executeCommand(parameters);

        // Assert
        assertEquals("Story size on StoryTitle1 was changed to Large", result);
      //  assertEquals(Size.LARGE, taskManagementRepository.getStories().get(0).getSize());
    }

    @Test
    public void executeCommand_ThrowsException_WhenStoryNotFound() {
        // Arrange
        List<String> parameters = Arrays.asList("999", "LARGE");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeSizeInStory.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenSizeIsInvalid() {
        // Create person and story
        Person person = taskManagementRepository.createPerson("John Doe");
        Story story = taskManagementRepository.createStory("StoryTitle1", "StoryDescription", Priority.LOW, StoryStatus.INPROGRESS, Size.MEDIUM, person);

        // Add story to repository
        taskManagementRepository.getStories().add(story);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(story.getId()), "INVALID_SIZE");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeSizeInStory.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenStoryIdIsInvalid() {
        // Arrange
        List<String> parameters = Arrays.asList("invalid_id", "LARGE");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeSizeInStory.executeCommand(parameters));
    }
}
