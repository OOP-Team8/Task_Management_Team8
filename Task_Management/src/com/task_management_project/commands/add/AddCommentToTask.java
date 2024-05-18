package com.task_management_project.commands.add;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class AddCommentToTask extends BaseCommand {

    public AddCommentToTask(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        if (parameters.size() < 2) {
//            return "Insufficient parameters. Please provide the task ID and the comment text.";
//        }
//
//        int taskId;
//        try {
//            taskId = Integer.parseInt(parameters.get(0));
//        } catch (NumberFormatException e) {
//            return "Invalid task ID. Please provide a valid integer ID.";
//        }
//        String commentText = parameters.get(1);
//
//        if (!taskManagementRepository.taskExists(taskId)) {
//            return "Task with the provided ID does not exist.";
//        }
//
//        Task task = taskManagementRepository.getTaskById(taskId);
//        task.addComment(new Comment(commentText));
//
//        return "Comment successfully added to the task.";
        return null;
    }
}
