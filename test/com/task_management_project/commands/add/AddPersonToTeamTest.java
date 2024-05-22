package com.task_management_project.commands.add;

import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddPersonToTeamTest {
    private TaskManagementRepository taskManagementRepository;
    private AddPersonToTeam addPersonToTeam;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        addPersonToTeam = new AddPersonToTeam(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> parameters = Arrays.asList("John Doe");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> addPersonToTeam.execute(parameters));
    }

    @Test
    public void executeCommand_AddsPersonToTeamSuccessfully() {
        // Create person and team
        Person person = taskManagementRepository.createPerson("John Doe");
        Team team = taskManagementRepository.createTeam("TeamA");

        // Add person and team to repository
        taskManagementRepository.addPerson(person);
        taskManagementRepository.addTeam(team);

        // Arrange
        List<String> parameters = Arrays.asList("John Doe", "TeamA");

        // Act
        String result = addPersonToTeam.executeCommand(parameters);

        // Assert
        assertEquals("John Doe was added to the team", result);
        assertEquals(1, team.getMembers().size());
        assertEquals(person, team.getMembers().get(0));
    }

    @Test
    public void executeCommand_ThrowsException_WhenPersonNotFound() {
        // Create team
        Team team = taskManagementRepository.createTeam("TeamA");

        // Add team to repository
        taskManagementRepository.addTeam(team);

        // Arrange
        List<String> parameters = Arrays.asList("John Doe", "TeamA");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> addPersonToTeam.executeCommand(parameters));
    }

    @Test
    public void executeCommand_ThrowsException_WhenTeamNotFound() {
        // Create person
        Person person = taskManagementRepository.createPerson("John Doe");

        // Add person to repository
        taskManagementRepository.addPerson(person);

        // Arrange
        List<String> parameters = Arrays.asList("John Doe", "TeamA");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> addPersonToTeam.executeCommand(parameters));
    }
    @Test
    public void executeCommand_ThrowsException_WhenPersonNameIsEmpty() {
        // Create team
        Team team = taskManagementRepository.createTeam("TeamA");

        // Add team to repository
        taskManagementRepository.addTeam(team);

        // Arrange
        List<String> parameters = Arrays.asList("", "TeamA");

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> addPersonToTeam.executeCommand(parameters));
    }
}