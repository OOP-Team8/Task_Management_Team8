package com.task_management_project.commands.listing;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.models.enums.FeedbackStatus;
import com.task_management_project.models.enums.StoryStatus;
import com.task_management_project.utils.ListingHelpers;
import com.task_management_project.utils.ParsingHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllStories extends BaseCommand {

    private StoryStatus status = null;
    private String personName = null;
    private String sortBy = null;

    public ListAllStories(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        List<Story> stories = getTaskManagementRepository().getStories();

        for (String param : parameters) {
            if (checkStatus(param)) {
                status = ParsingHelpers.tryParseEnum(param, StoryStatus.class);
            } else if(checkSortBy(param)) {
                sortBy = param;
            } else {
                personName = param;
            }
        }

        return findStory(status,personName,sortBy,stories);
    }

    public String findStory(StoryStatus status, String personName, String sortBy, List<Story> list){

        if (list.isEmpty()) {
            return "No bugs found with the specified criteria.";
        }

        if(status != null){
            list = list.stream()
                    .filter(story -> story.getStatus() == status)
                    .collect(Collectors.toList());
        }
        if (personName != null){
            list = list.stream()
                    .filter(story -> story.getPerson().getName().equals(personName))
                    .collect(Collectors.toList());
        }

        if (sortBy != null) {
            switch (sortBy.toLowerCase()) {
                case "title" -> list.sort(Comparator.comparing(Story::getTitle));
                case "priority" -> list.sort(Comparator.comparing(Story::getPriority));
                case "size" -> list.sort(Comparator.comparing(Story::getSize));
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
        if(param.equalsIgnoreCase("priority")){
            return true;
        }
        if(param.equalsIgnoreCase("size")){
            return true;
        }
        return false;
    }
}
