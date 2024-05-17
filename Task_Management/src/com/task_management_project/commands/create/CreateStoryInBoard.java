package com.task_management_project.commands.create;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Board;
import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.*;
import com.task_management_project.utils.ParsingHelpers;
import com.task_management_project.utils.Validation;

import java.util.List;

public class CreateStoryInBoard extends BaseCommand {

    private final static String STORY_ADDED_SUCCESSFULLY = "Story %s added to board %s successfully!";
    private final int EXPECTED_PARAMS = 7;

    public CreateStoryInBoard(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        StoryStatus storyStatus = ParsingHelpers.tryParseEnum(parameters.get(2),StoryStatus.class);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(3),Priority.class);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(4),Size.class);
        Person person = getTaskManagementRepository().findPersonByName(parameters.get(5));
        String boardName = parameters.get(6);


        return createStory(title, description,priority, storyStatus, size,person,boardName);
    }

    private String createStory( String title, String description,Priority priority, StoryStatus storyStatus, Size size,Person person,String boardName){
        Story story = getTaskManagementRepository().createStory(title,description,priority,storyStatus,size,person);
        Board board = getTaskManagementRepository().findBoardByName(boardName);
        getTaskManagementRepository().addTaskToBoard(story,board);
        getTaskManagementRepository().addTaskToMember(story, person);

        return String.format(STORY_ADDED_SUCCESSFULLY,title,boardName);
    }
}
