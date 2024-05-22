package com.task_management_project.commands.add;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.EventLogImpl;
import com.task_management_project.models.contracts.*;
import com.task_management_project.utils.Validation;

import java.util.List;

public class AddCommentToTask extends BaseCommand {
    private final int EXPECTED_PARAMS = 5;
    private final String COMMENT_ADDED_SUCCESSFULLY = "%s added comment to task - %s - %s";

    public AddCommentToTask(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_PARAMS);
        Team team = getTaskManagementRepository().findTeamByName(parameters.get(0));
        Board board = getTaskManagementRepository().findBoardByName(parameters.get(1));
        Task task = getTaskManagementRepository().findTaskById(Integer.parseInt(parameters.get(2)));
        Person person = getTaskManagementRepository().findPersonByName(parameters.get(3));
        Comment comment = getTaskManagementRepository().createComment(person, parameters.get(4));

        task.addComment(comment);
        //getTaskManagementRepository().findTaskById(task.getId()).getChangesList().add(new EventLogImpl(String.format(comment.toString())));

        return String.format(COMMENT_ADDED_SUCCESSFULLY,person.getName(), task.getTitle(), comment.getContent());
    }
}
