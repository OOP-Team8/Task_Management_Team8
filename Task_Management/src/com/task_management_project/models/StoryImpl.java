package com.task_management_project.models;

import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.Size;
import com.task_management_project.models.enums.StoryStatus;

public class StoryImpl extends TaskImpl implements Story {
    public StoryImpl(int id, String title, String description, Priority priority, StoryStatus storyStatus, Size size, Person person) {
        super(id, title, description);
    }



    //TODO
    @Override
    public String getAsString() {
        return null;
    }
}
