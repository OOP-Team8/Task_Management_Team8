package com.task_management_project.commands.change;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class ChangeSizeInStory extends BaseCommand {

    public ChangeSizeInStory(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        if (parameters.size() < 1) {
//            return "Usage: show_all_team_boards [team_name]";
//        }
//
//        String teamName = parameters.get(0);
//
//        Team team = taskManagementRepository.getTeamByName(teamName);
//
//        if (team == null) {
//            return String.format("Team '%s' not found.", teamName);
//        }
//
//        List<Board> boards = team.getBoards();
//
//        StringBuilder result = new StringBuilder();
//        result.append("Team Boards:\n");
//        for (Board board : boards) {
//            result.append(board.getName()).append("\n");
//        }
//
//        return result.toString();
        return null;
    }
}
