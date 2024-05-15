package com.task_management_project.core.contracts;

import com.task_management_project.models.contracts.*;

import java.util.List;

public interface TaskManagementRepository {

    Person addPerson(Person personToAdd);

    Person findPersonByName(String name);

    Person createPerson(String name, List<Task> tasks, List<Story> history);

    Team createTeam(String name);

    Bug createBug(int id, String title, String description);

    Board createBoard(String name, List<Task> boardTasks,List<EventLog> eventLogList);
}
