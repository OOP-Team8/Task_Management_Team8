package com.task_management_project.commands.listing;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.contracts.Feedback;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.models.enums.FeedbackStatus;
import com.task_management_project.models.enums.StoryStatus;
import com.task_management_project.models.enums.TaskType;
import com.task_management_project.utils.ListingHelpers;
import com.task_management_project.utils.ParsingHelpers;
import com.task_management_project.utils.Validation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ListTasksWithAssignee extends BaseCommand {
    private String status = null;
    private String personName = null;


    public ListTasksWithAssignee(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        List<Task> tasks = getTaskManagementRepository().getTasks();

        for (String param : parameters) {
            if (isStatus(param)) {
                status = param;
            }else {
                personName = param;
            }
        }

        return findTasks(status, personName, tasks);
    }

    private boolean isStatus(String parameter) {
        return ParsingHelpers.isItStatus(parameter,BugStatus.class)
                || ParsingHelpers.isItStatus(parameter,StoryStatus.class)
                || ParsingHelpers.isItStatus(parameter,FeedbackStatus.class);
    }

    private String findTasks(String status, String personName, List<Task> list) {

        if (list.isEmpty()) {
            return "No tasks found with the specified criteria.";
        }

        List<Task> filteredList = list.stream()
                .filter(task -> {
                    if (status != null) {
                        if (task.getType() == TaskType.BUG) {
                            return ((Bug) task).getStatus().toString().equalsIgnoreCase(status);
                        } else if (task.getType() == TaskType.STORY) {
                            return ((Story) task).getStatus().toString().equalsIgnoreCase(status);
                        } else if (task.getType() == TaskType.FEEDBACK) {
                            return ((Feedback) task).getStatus().toString().equalsIgnoreCase(status);
                        }
                    }
                    return true;
                })
                .filter(task -> personName == null || task.getAssignable() != false || (task.getPerson() != null && task.getPerson().getName().equalsIgnoreCase(personName)))
                .sorted(Comparator.comparing(Task::getTitle))
                .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            return "No tasks found with the specified criteria.";
        }

        return ListingHelpers.listElements(filteredList);
    }
}