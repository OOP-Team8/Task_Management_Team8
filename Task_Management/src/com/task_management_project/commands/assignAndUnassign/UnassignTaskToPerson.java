package com.task_management_project.commands.assignAndUnassign;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class UnassignTaskToPerson extends BaseCommand {

    public UnassignTaskToPerson(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        if (parameters.size() < 2) {
//            return "Insufficient parameters. Please provide the team name and the board name.";
//        }
//
//        String teamName = parameters.get(0);
//        String boardName = parameters.get(1);
//
//        if (!taskManagementRepository.teamExists(teamName)) {
//            return "Team with the provided name does not exist.";
//        }
//
//        Team team = taskManagementRepository.getTeamByName(teamName);
//        if (team.boardExists(boardName)) {
//            return "Board with the provided name already exists in the team.";
//        }
//
//        Board newBoard = new Board(boardName);
//        team.addBoard(newBoard);
//
//        return String.format("Board '%s' successfully created in team '%s'.", boardName, teamName);
        return null;
    }
}
