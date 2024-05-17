package com.task_management_project.models;

import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.Size;
import com.task_management_project.models.enums.StoryStatus;

public class StoryImpl extends TaskImpl implements Story {

    private  Priority priority;
    private  StoryStatus storyStatus;
    private Size size;
    private Person person;

    public StoryImpl(int id, String title, String description, Priority priority, StoryStatus storyStatus, Size size, Person person) {
        super(id, title, description);
        this.priority = priority;
        this.storyStatus = storyStatus;
        this.size = size;
        this.person = person;
    }

    @Override
    public Priority getPriority(){
        return this.priority;
    }

    @Override
    public StoryStatus getStatus() {
        return this.storyStatus;
    }

    @Override
    public Size getSize() {
        return this.size;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    //TODO
    @Override
    public String getAsString() {
        return null;
    }
}
