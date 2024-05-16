package com.task_management_project.core.contracts;

import com.task_management_project.models.contracts.*;

import java.util.List;

public interface TaskManagementRepository {

    void addPerson(Person personToAdd);
    List<Person> getPeople();
    List<Task> getTasks();
    List<Board> getBoards();
    List<Team> getTeams();

    Person findPersonByName(String name);

    Person createPerson(String name);
    Task findTaskById(int id);

    Team createTeam(String name);
    Team findTeamByName(String name);

    Bug createBug(int id, String title, String description);

    Board createBoard(String name);
}
