package com.task_management_project.core;

import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.BoardImpl;
import com.task_management_project.models.BugImpl;
import com.task_management_project.models.PersonImpl;
import com.task_management_project.models.TeamImpl;
import com.task_management_project.models.contracts.*;
import com.task_management_project.models.enums.BugSeverity;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.utils.Validation;
import com.task_management_project.utils.contracts.DataValidation;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl  implements TaskManagementRepository {
    private List<Person> people;
    private List<Task> tasks;
    private List<Board> boards;
    private List<Team> teams;
    public TaskManagementRepositoryImpl(){
        people = new ArrayList<>();
        tasks = new ArrayList<>();
        boards = new ArrayList<>();
        teams = new ArrayList<>();
    }

    @Override
    public List<Person> getPeople() {
        return new ArrayList<>(people);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public void addPerson(Person personToAdd) {
        if (people.contains(personToAdd)){
            throw new IllegalArgumentException("Already in the list");
        }
        this.people.add(personToAdd);
    }

    @Override
    public Person findPersonByName(String name) {
        return people.stream().filter(p ->p.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(()-> new IllegalArgumentException("This person doesn't exist!"));
    }

    @Override
    public Person createPerson(String name) {
        Validation.validateDubs(people,name);
        Person person =  new PersonImpl(name);
        people.add(person);
        return person;
    }

    @Override
    public Task findTaskById(int id) {
        return tasks.stream().filter(t -> t.getId()==id).findFirst().orElseThrow(()-> new IllegalArgumentException("This task doesn't exist!"));
    }

    @Override
    public Team createTeam(String name) {
        Validation.validateDubs(teams,name);
        Team team = new TeamImpl(name);
        teams.add(team);
        return team;
    }
    @Override
    public Team findTeamByName(String name) {
        return teams.stream().filter(t ->t.getName().equals(name)).findFirst().orElseThrow(() -> new IllegalArgumentException("This team doesn't exist!"));
    }

    @Override
    public Bug createBug(int id, String title, String description, Priority priority, BugStatus bugStatus, BugSeverity bugSeverity,Person person) {
        Bug bug = new BugImpl(++id,title,description,priority,bugStatus,bugSeverity,person);
        tasks.add(bug);
        return bug;
    }

    @Override
    public Board createBoard(String name) {
        return new BoardImpl(name);
    }
}
