package com.task_management_project.core.contracts;

import com.task_management_project.models.contracts.*;
import com.task_management_project.models.enums.*;

import java.lang.reflect.Member;
import java.util.List;

public interface TaskManagementRepository {

    List<Bug> getBugs();
    List<Story> getStories();
    List<Feedback> getFeedbacks();
    List<Person> getPeople();
    List<Task> getTasks();
    List<Board> getBoards();
    List<Team> getTeams();

    void addPerson(Person personToAdd);
    void addTeam(Team team);
    void addTaskToBoard(Task task, Board board);
    void addTaskToMember(Task task, Person member);

    Person findPersonByName(String name);
    Task findTaskById(int id);
    Bug findBugByTitle(String title);
    Story findStoryByTitle(String title);
    Feedback findFeedbackByTitle(String title);
    Team findTeamByName(String name);
    Board findBoardByName(String name);


    Person createPerson(String name);
    Team createTeam(String name);
    Bug createBug(String title, String description, Priority priority, BugStatus bugStatus, BugSeverity bugSeverity,Person person);
    Story createStory(String title, String description, Priority priority, StoryStatus storyStatus, Size size, Person person);
    Board createBoard(String name);
    Feedback createFeedback(String title, String description, FeedbackStatus status, int rating);

}
