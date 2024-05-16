package com.task_management_project.commands.show;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Board;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Team;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ShowAllTeamBoards extends BaseCommand {
    public final int EXPECTED_PARAMS = 0;
    public ShowAllTeamBoards(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    // This command shows all created boards
    // May be needs to be fixed

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);
        StringBuilder builder = new StringBuilder("--BOARDS--" + System.lineSeparator());
        for (Board board : getTaskManagementRepository().getBoards()) {
            builder.append(getTaskManagementRepository().getBoards().indexOf(board)+1 + ". " + board.getName() +
                    ((getTaskManagementRepository().getBoards().indexOf(board)+1 < getTaskManagementRepository().getBoards().size()) ? System.lineSeparator() : ""));
        }
        return builder.toString();
    }
}
