package com.task_management_project.core;

import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.*;
import com.task_management_project.models.contracts.*;
import com.task_management_project.models.enums.*;
import com.task_management_project.utils.Validation;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl  implements TaskManagementRepository {
    private static int id;
    private List<Person> people;
    private List<Task> tasks;
    private List<Board> boards;
    private List<Team> teams;
    private List<Bug> bugs;
    private List<Feedback> feedbacks;
    private List<Story> stories;
    public TaskManagementRepositoryImpl(){
        id = 0;
        people = new ArrayList<>();
        tasks = new ArrayList<>();
        boards = new ArrayList<>();
        teams = new ArrayList<>();
        bugs = new ArrayList<>();
        feedbacks = new ArrayList<>();
        stories = new ArrayList<>();
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
    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
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
    public Bug findBugByTitle(String title) {
        return bugs.stream().filter(t -> t.getTitle()==title).findFirst().orElseThrow(()-> new IllegalArgumentException("This title doesn't exist!"));
    }
    @Override
    public Story findStoryByTitle(String title) {
        return stories.stream().filter(t -> t.getTitle()==title).findFirst().orElseThrow(()-> new IllegalArgumentException("This title doesn't exist!"));
    }
    @Override
    public Feedback findFeedbackByTitle(String title) {
        return feedbacks.stream().filter(t -> t.getTitle()==title).findFirst().orElseThrow(()-> new IllegalArgumentException("This title doesn't exist!"));
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
    public Bug createBug(String title, String description, Priority priority, BugStatus bugStatus, BugSeverity bugSeverity,Person person) {
        Bug bug = new BugImpl(++id,title,description,priority,bugStatus,bugSeverity,person);
        bugs.add(bug);
      //  tasks.add(bug);
        return bug;
    }
    @Override
    public Feedback createFeedback(String title, String description, FeedbackStatus status, int rating){
        Feedback feedback = new FeedbackImpl(++id,title,description,status,rating);
        feedbacks.add(feedback);
        //tasks.add(feedback);
        return feedback;
    }
    @Override
    public Story createStory(String title, String description, Priority priority, StoryStatus storyStatus, Size size, Person person){
        Story story = new StoryImpl(++id,title,description,priority,storyStatus,size,person);
        stories.add(story);
        //tasks.add(story);
        return story;
    }

    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        boards.add(board);
        return board;
    }
}
