package com.task_management_project.core.contracts;

import com.task_management_project.models.contracts.*;
import com.task_management_project.models.enums.BugSeverity;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.models.enums.Priority;

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

    Bug createBug(int id, String title, String description, Priority priority, BugStatus bugStatus, BugSeverity bugSeverity,Person person);

    Board createBoard(String name);
}
