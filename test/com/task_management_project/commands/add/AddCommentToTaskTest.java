package com.task_management_project.commands.add;


import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Board;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.models.contracts.Team;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.Size;
import com.task_management_project.models.enums.StoryStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommentToTaskTest {
    private TaskManagementRepository taskManagementRepository;
    private AddCommentToTask addCommentToTask;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        addCommentToTask = new AddCommentToTask(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> parameters = Arrays.asList("TeamA", "John Doe", "This is a comment");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> addCommentToTask.execute(parameters));
    }

    @Test
    public void executeCommand_AddsCommentSuccessfully() {
        // Create team, board, person, and task
        Team team = taskManagementRepository.createTeam("TeamA");
        Board board = taskManagementRepository.createBoard("BoardA");
        Person person = taskManagementRepository.createPerson("John Doe");

        Task task = taskManagementRepository.createStory("TaskTitle1", "TaskDescription", Priority.HIGH, StoryStatus.INPROGRESS, Size.LARGE, person);

        // Add team, board, person, and task to repository
        taskManagementRepository.addTeam(team);
        team.addBoard(board);
        board.addTask(task);
        taskManagementRepository.addPerson(person);

        // Arrange
        List<String> parameters = Arrays.asList("TeamA", "BoardA", String.valueOf(task.getId()), "John Doe", "This is a comment");

        // Act
        String result = addCommentToTask.executeCommand(parameters);

        // Assert
        assertEquals("John Doe added comment to task - TaskTitle1 - This is a comment", result);
    }

    @Test
    public void executeCommand_AddsComment_Throw_exeption_WhenCommentIsNotValid() {
        // Create team, board, person, and task
        Team team = taskManagementRepository.createTeam("TeamA");
        Board board = taskManagementRepository.createBoard("BoardA");
        Person person = taskManagementRepository.createPerson("John Doe");

        Task task = taskManagementRepository.createStory("TaskTitle1", "TaskDescription", Priority.HIGH, StoryStatus.INPROGRESS, Size.LARGE, person);

        // Add team, board, person, and task to repository
        taskManagementRepository.addTeam(team);
        team.addBoard(board);
        board.addTask(task);
        taskManagementRepository.addPerson(person);

        // Arrange
        List<String> parameters = Arrays.asList("TeamA", "BoardA", String.valueOf(task.getId()), "John Doe", "comment");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> addCommentToTask.execute(parameters));
    }
}