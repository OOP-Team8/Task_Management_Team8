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

public class ChangeStatusInStoryTest {
    private TaskManagementRepository taskManagementRepository;
    private ChangeStatusInStory changeStatusInStory;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        changeStatusInStory = new ChangeStatusInStory(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> parameters = Arrays.asList("1");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInStory.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_TooManyArguments() {
        // Arrange
        List<String> parameters = Arrays.asList("1", "IN_PROGRESS", "Extra Argument");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInStory.execute(parameters));
    }

    @Test
    public void executeCommand_ChangesStatusSuccessfully() {
        // Create person
        Person person = taskManagementRepository.createPerson("John Doe");

        // Create story
        Story story = taskManagementRepository.createStory("StoryTitle1", "StoryDescription", Priority.LOW, StoryStatus.NOTDONE,Size.LARGE, person);

        // Add story to repository
        taskManagementRepository.getStories().add(story);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(story.getId()), "INPROGRESS");

        // Act
        String result = changeStatusInStory.executeCommand(parameters);

        // Assert
        assertEquals("Story status on StoryTitle1 was changed to In Progress", result);
       // assertEquals(StoryStatus.INPROGRESS, story.getStatus());
    }

    @Test
    public void executeCommand_ThrowsException_WhenStoryNotFound() {
        // Arrange
        List<String> parameters = Arrays.asList("999", "INPROGRESS");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInStory.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenStatusIsInvalid() {
        // Create person
        Person person = taskManagementRepository.createPerson("John Doe");

        // Create story
        Story story = taskManagementRepository.createStory("StoryTitle1", "StoryDescription", Priority.LOW, StoryStatus.NOTDONE, Size.LARGE,person);

        // Add story to repository
        taskManagementRepository.getStories().add(story);

        // Arrange
        List<String> parameters = Arrays.asList(String.valueOf(story.getId()), "INVALID_STATUS");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInStory.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenStoryIdIsInvalid() {
        // Arrange
        List<String> parameters = Arrays.asList("invalid_id", "INPROGRESS");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeStatusInStory.executeCommand(parameters));
    }
}
