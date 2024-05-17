package com.task_management_project.commands.create;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Board;
import com.task_management_project.models.contracts.Team;
import com.task_management_project.utils.Validation;

import java.util.List;

public class CreateNewBoardInTeam extends BaseCommand {
    public final int EXPECTED_PARAMS = 1;
    private final static String BOARD_CREATED_SUCCESSFULLY = "Board %s created in Team %s successfully!";

    public CreateNewBoardInTeam(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        String name = parameters.get(0);
        String teamName = parameters.get(1);

        return createBoard(name,teamName);
    }

    public String createBoard(String name,String teamName){
        Board board = getTaskManagementRepository().createBoard(name);
        Team team = getTaskManagementRepository().findTeamByName(teamName);
        team.addBoard(board);

        return String.format(BOARD_CREATED_SUCCESSFULLY,name,teamName);
    }
}
