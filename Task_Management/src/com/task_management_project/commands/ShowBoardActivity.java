package com.task_management_project.commands;

import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class ShowBoardActivity extends BaseCommand{
    public ShowBoardActivity(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        if (parameters.size() < 1) {
//            return "Usage: show_board_activity [board_name]";
//        }
//
//        String boardName = parameters.get(0);
//
//        Board board = taskManagementRepository.getBoardByName(boardName);
//
//        if (board == null) {
//            return String.format("Board '%s' not found.", boardName);
//        }
//
//        List<EventLog> events = board.getChangesList();
//
//
//        StringBuilder result = new StringBuilder();
//        result.append("Board Activity:\n");
//        for (EventLog event : events) {
//            result.append(event.getDescription()).append("\n");
//        }
//
//        return result.toString();
//    }
        return null;
    }
}
