package com.task_management_project.core;

import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.*;

import java.util.List;

public class TaskManagementRepositoryImpl  implements TaskManagementRepository {


    @Override
    public Person addPerson(Person personToAdd) {
        return null;
    }

    @Override
    public Person findPersonByName(String name) {
        return null;
    }

    @Override
    public Person createPerson(String name, List<Task> tasks, List<Story> history) {
        return null;
    }

    @Override
    public Team createTeam(String name) {
        return null;
    }

    @Override
    public Bug createBug(int id, String title, String description) {
        return null;
    }

    @Override
    public Board createBoard(String name, List<Task> boardTasks, List<EventLog> eventLogList) {
        return null;
    }
}
