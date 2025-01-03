package com.task_management_project.commands.listing;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.contracts.Feedback;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.models.enums.FeedbackStatus;
import com.task_management_project.utils.ListingHelpers;
import com.task_management_project.utils.ParsingHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllFeedbacks extends BaseCommand {

    private FeedbackStatus status = null;
    private String sortBy = null;

    public ListAllFeedbacks(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        List<Feedback> feedbacks = getTaskManagementRepository().getFeedbacks();

        for (String param : parameters) {
            if (checkStatus(param)) {
                status = ParsingHelpers.tryParseEnum(param,FeedbackStatus.class);
            } else if(checkSortBy(param)) {
                sortBy = param;
            }
        }

        return findFeedback(status,sortBy,feedbacks);
    }

    public String findFeedback(FeedbackStatus status, String sortBy, List<Feedback> list){

        if (list.isEmpty()) {
            return "No bugs found with the specified criteria.";
        }

        if(status != null){
            list = list.stream()
                    .filter(feedback -> feedback.getStatus() == status)
                    .collect(Collectors.toList());
        }

        if (sortBy != null) {
            switch (sortBy.toLowerCase()) {
                case "title" -> list.sort(Comparator.comparing(Feedback::getTitle));
                case "rating" -> list.sort(Comparator.comparing(Feedback::getRating));
                default -> throw new IllegalArgumentException("Invalid sort parameter.");
            }
        }


        return ListingHelpers.listElements(list);
    }


    private boolean checkStatus(String param){
        try {
            BugStatus.valueOf(param);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean checkSortBy(String param) {
        if (param.equalsIgnoreCase("title")){
            return true;
        }
        if(param.equalsIgnoreCase("rating")){
            return true;
        }
        return false;
    }
}
