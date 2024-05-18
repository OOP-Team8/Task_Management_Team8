package com.task_management_project.models;

import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.Size;
import com.task_management_project.models.enums.StoryStatus;

public class StoryImpl extends TaskImpl implements Story {
    private Priority priority;
    private StoryStatus status;
    private Size size;
    private Person person;

    public StoryImpl(int id, String title, String description, Priority priority, StoryStatus status, Size size, Person person) {
        super(id, title, description);
        setPriority(priority);
        setStatus(status);
        setSize(size);
        this.person = person;
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public StoryStatus getStatus() {
        return this.status;
    }

    private void setStatus(StoryStatus status) {
        this.status = status;
    }

    @Override
    public Size getSize() {
        return this.size;
    }

    private void setSize(Size size) {
        this.size = size;
    }

    @Override
    public Person getPerson() {
        return this.person;
    }

    //TODO
    @Override
    public String getAsString() {
        return null;
    }
}
