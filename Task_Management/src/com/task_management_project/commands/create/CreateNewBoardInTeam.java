package com.task_management_project.commands.create;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Board;
import com.task_management_project.utils.Validation;

import java.util.List;

public class CreateNewBoardInTeam extends BaseCommand {
    public final int EXPECTED_PARAMS = 1;
    public CreateNewBoardInTeam(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);
        Board board = getTaskManagementRepository().createBoard(parameters.get(0));
        return board.getName();
    }
}
