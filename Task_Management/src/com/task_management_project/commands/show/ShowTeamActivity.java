package com.task_management_project.commands.show;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Board;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.models.contracts.Team;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ShowTeamActivity extends BaseCommand {
    public final int EXPECTED_PARAMS = 1;
    public ShowTeamActivity(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);
        Team team = getTaskManagementRepository().findTeamByName(parameters.get(0));
        StringBuilder builder = new StringBuilder(String.format("--%s - ACTIVITIES--",team.getName())).append(System.lineSeparator());
        for (Board board : team.getBoards()) {
            builder.append(String.format("BOARD - %s",board.getName())).append(System.lineSeparator());
            for (Task task : board.getTasks()) {
                builder.append(board.getTasks().indexOf(task) + 1).append(". ").append(task.getTitle()).append(board.getTasks().indexOf(task) < board.getTasks().size() - 1 ? System.lineSeparator() : "");
            }
        }
        return builder.toString();
    }
}
