package com.task_management_project.commands.show;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Board;import com.task_management_project.models.contracts.EventLog;
import com.task_management_project.models.contracts.Team;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ShowBoardActivity extends BaseCommand {
    public final int EXPECTED_PARAMS = 2;
    public ShowBoardActivity(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);
        Board board = getTaskManagementRepository().findBoardByName(parameters.get(0));
        Team team = getTaskManagementRepository().findTeamByName(parameters.get(1));
        StringBuilder builder = new StringBuilder(String.format("--%s - %s--",team.getName(),board.getName())).append(System.lineSeparator());
        Validation.message(builder, board,board.getLogs());
        return builder.toString();
    }
}
