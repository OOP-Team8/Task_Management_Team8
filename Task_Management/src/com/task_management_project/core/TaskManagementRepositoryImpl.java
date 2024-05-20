package com.task_management_project.core;

import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.*;
import com.task_management_project.models.contracts.*;
import com.task_management_project.models.enums.*;
import com.task_management_project.utils.Validation;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {
    private static int nextId;
    private final List<Person> people;
    private final List<Task> tasks;
    private final List<Board> boards;
    private final List<Team> teams;
    private final List<Bug> bugs;
    private final List<Feedback> feedbacks;
    private final List<Story> stories;

    public TaskManagementRepositoryImpl() {
        people = new ArrayList<>();
        tasks = new ArrayList<>();
        boards = new ArrayList<>();
        teams = new ArrayList<>();
        bugs = new ArrayList<>();
        feedbacks = new ArrayList<>();
        stories = new ArrayList<>();
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
    public void addComment(Task task, Comment comment) {
        task.getCommentList().add(comment);
    }

    @Override
    public Comment createComment(Person author, String content) {
        return new CommentImpl(author, content);
    }

    @Override
    public void addPerson(Person personToAdd) {
        if (people.contains(personToAdd)) {
            throw new IllegalArgumentException("Already in the list");
        }
        this.people.add(personToAdd);
    }

    //ADDS TEAM TO LIST TEAMS
    @Override
    public void addTeam(Team team) {
        if (teams.contains(team)) {
            throw new IllegalArgumentException("Already in the list");
        }
        this.teams.add(team);
    }

    @Override
    public Person findPersonByName(String name) {
        return people.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new IllegalArgumentException("This person doesn't exist!"));
    }

    @Override
    public Person createPerson(String name) {
        Validation.validateDubs(people, name);
        Person person = new PersonImpl(name);
        return person;
    }

    @Override
    public Task findTaskById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("This task doesn't exist!"));
    }

    @Override
    public Bug findBugByTitle(String title) {
        return bugs.stream().filter(t -> t.getTitle() == title).findFirst().orElseThrow(() -> new IllegalArgumentException("This title doesn't exist!"));
    }

    @Override
    public Story findStoryByTitle(String title) {
        return stories.stream().filter(t -> t.getTitle() == title).findFirst().orElseThrow(() -> new IllegalArgumentException("This title doesn't exist!"));
    }

    @Override
    public Feedback findFeedbackByTitle(String title) {
        return feedbacks.stream().filter(t -> t.getTitle() == title).findFirst().orElseThrow(() -> new IllegalArgumentException("This title doesn't exist!"));
    }

    @Override
    public Team createTeam(String name) {
        Validation.validateDubs(teams, name);
        Team team = new TeamImpl(name);
        return team;
    }

    @Override
    public Team findTeamByName(String name) {
        return teams.stream().filter(t -> t.getName().equals(name)).findFirst().orElseThrow(() -> new IllegalArgumentException("This team doesn't exist!"));
    }

    @Override
    public Board findBoardByName(String name) {
        return boards.stream().filter(b -> b.getName().equals(name)).findFirst().orElseThrow(() -> new IllegalArgumentException("This board doesn't exist!"));
    }

    public static  <T extends Task> T findById(int id, List<T> list){
        return list.stream().filter(t -> t.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("This task doesn't exist!"));
    }
    @Override
    public Bug createBug(String title, String description, Priority priority, BugStatus bugStatus, BugSeverity bugSeverity, Person person) {
        Bug bug = new BugImpl(++nextId, title, description, priority, bugStatus, bugSeverity, person);
        bugs.add(bug);
        tasks.add(bug);
        return bug;
    }

    @Override
    public Feedback createFeedback(String title, String description, FeedbackStatus status, int rating) {
        return null;
    }

    @Override
    public Feedback createFeedback(String title, String description, FeedbackStatus status, int rating, Person person) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, status, rating, person);
        feedbacks.add(feedback);
        tasks.add(feedback);
        return feedback;
    }

    @Override
    public Story createStory(String title, String description, Priority priority, StoryStatus storyStatus, Size size, Person person) {
        Story story = new StoryImpl(++nextId, title, description, priority, storyStatus, size, person);
        stories.add(story);
        tasks.add(story);
        return story;
    }

    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        boards.add(board);
        return board;
    }

    @Override
    public void addTaskToBoard(Task task, Board board) {
        findBoardByName(board.getName()).addTask(task);

    }

    @Override
    public void addTaskToMember(Task task, Person assignee) {
        if (!people.contains(assignee)){
            throw new IllegalArgumentException("This person doesn't exist!");
        }
        if (!task.getPerson().equals(assignee)){
            assignee.addEvent(new EventLogImpl(String.format("%s take a new task - %s from %s",assignee.getName(),task.getTitle(),task.getPerson().getName())));
        }

        task.getPerson().addEvent(new EventLogImpl(String.format("%s take a new task - %s",assignee.getName(),task.getTitle())));
        assignee.addTask(task);
    }

    @Override
    public void changeBugPriority(Bug bug, Priority priority) {
        TaskManagementRepositoryImpl.findById(bug.getId(), bugs);
        bugs.remove(bug);
        tasks.remove(bug);
        Bug bugItem = new BugImpl(bug.getId(),bug.getTitle(),bug.getDescription(),priority,bug.getStatus(),bug.getSeverity(),bug.getPerson());
        bugs.add(bugItem);
        tasks.add(bugItem);
    }

    @Override
    public void changeStoryPriority(Story story, Priority priority) {
        TaskManagementRepositoryImpl.findById(story.getId(), stories);
        stories.remove(story);
        tasks.remove(story);
        Story storyItem = new StoryImpl(story.getId(),story.getTitle(),story.getDescription(),priority,story.getStatus(),story.getSize(),story.getPerson());
        stories.add(storyItem);
        tasks.add(storyItem);
    }

    @Override
    public void changeStorySize(Story story, Size size) {
        TaskManagementRepositoryImpl.findById(story.getId(), stories);
        stories.remove(story);
        tasks.remove(story);
        Story storyItem = new StoryImpl(story.getId(),story.getTitle(),story.getDescription(),story.getPriority(),story.getStatus(),story.getSize(),story.getPerson());
        stories.add(storyItem);
        tasks.add(storyItem);
    }

    @Override
    public void changeBugStatus(Bug bug, BugStatus newStatus) {
        TaskManagementRepositoryImpl.findById(bug.getId(), bugs);
        bugs.remove(bug);
        tasks.remove(bug);
        Bug bugItem = new BugImpl(bug.getId(),bug.getTitle(),bug.getDescription(),bug.getPriority(),bug.getStatus(),bug.getSeverity(),bug.getPerson());
        bugs.add(bugItem);
        tasks.add(bugItem);
    }

    @Override
    public void changeStoryStatus(Story story, StoryStatus status) {
        TaskManagementRepositoryImpl.findById(story.getId(), stories);
        stories.remove(story);
        tasks.remove(story);
        Story storyItem = new StoryImpl(story.getId(),story.getTitle(),story.getDescription(),story.getPriority(),story.getStatus(),story.getSize(),story.getPerson());
        stories.add(storyItem);
        tasks.add(storyItem);
    }

    @Override
    public void changeBugSeverity(Bug bug, BugSeverity severity) {
        TaskManagementRepositoryImpl.findById(bug.getId(), bugs);
        bugs.remove(bug);
        tasks.remove(bug);
        Bug bugItem = new BugImpl(bug.getId(),bug.getTitle(),bug.getDescription(),bug.getPriority(),bug.getStatus(),bug.getSeverity(),bug.getPerson());
        bugs.add(bugItem);
        tasks.add(bugItem);
    }

    @Override
    public void changeFeedbackStatus(Feedback feedback, FeedbackStatus status) {
        TaskManagementRepositoryImpl.findById(feedback.getId(), feedbacks);
        feedbacks.remove(feedback);
        tasks.remove(feedback);
        Feedback feedbackItem = new FeedbackImpl(feedback.getId(),feedback.getTitle(),feedback.getDescription(),feedback.getStatus(),feedback.getRating(),feedback.getPerson());
        feedbacks.add(feedbackItem);
        tasks.add(feedbackItem);
    }

    @Override
    public void removeTaskFromPerson(Task task, Person assignee) {
        TaskManagementRepositoryImpl.findById(task.getId(),assignee.getTasks());
        if (!people.contains(assignee)){
            throw new IllegalArgumentException("This person doesn't exist!");
        }
        assignee.removeTask(task);
        Person person = task.getPerson();
        person.addEvent(new EventLogImpl(String.format("%s completed task - %s", assignee.getName(),task.getTitle())));
        assignee.addEvent(new EventLogImpl(String.format("The task with name - %s was complete.", task.getTitle())));
    }
}
