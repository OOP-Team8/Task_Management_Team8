package com.task_management_project.core.contracts;

import com.task_management_project.models.contracts.*;
import com.task_management_project.models.enums.*;

import java.util.List;

public interface TaskManagementRepository {

    List<Bug> getBugs();

    List<Story> getStories();

    List<Feedback> getFeedbacks();

    void addPerson(Person personToAdd);
    List<Person> getPeople();
    List<Task> getTasks();
    List<Board> getBoards();
    List<Team> getTeams();
    Person findPersonByName(String name);
    Person createPerson(String name);
    Task findTaskById(int id);
    Bug findBugByTitle(String title);

    Story findStoryByTitle(String title);

    Feedback findFeedbackByTitle(String title);

    Team createTeam(String name);
    Team findTeamByName(String name);
    Bug createBug(String title, String description, Priority priority, BugStatus bugStatus, BugSeverity bugSeverity,Person person);

    Story createStory(String title, String description, Priority priority, StoryStatus storyStatus, Size size, Person person);

    Board createBoard(String name);
    Feedback createFeedback(String title, String description, FeedbackStatus status, int rating);
}
