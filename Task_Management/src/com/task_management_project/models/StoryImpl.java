package com.task_management_project.models;

import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.Size;
import com.task_management_project.models.enums.StoryStatus;
import com.task_management_project.models.enums.TaskType;

public class  StoryImpl extends TaskImpl implements Story {
    private Priority priority;
    private StoryStatus status;
    private Size size;

    public StoryImpl(int id, String title, String description, Priority priority, StoryStatus status, Size size, Person person) {
        super(id, title, description, person);
        setPriority(priority);
        setStatus(status);
        setSize(size);
        this.type = TaskType.STORY;
    }
    @Override
    public Priority getPriority() {
        return this.priority;
    }
    @Override
    public StoryStatus getStatus() {
        return this.status;
    }
    @Override
    public Size getSize() {
        return this.size;
    }

    private void setStatus(StoryStatus status) {
        this.status = status;
    }
    private void setPriority(Priority priority) {
        this.priority = priority;
    }
    private void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String getAsString() {
       return String.format("%s\nPriority: %s\nStatus: %s\nSize: %s",super.getAsString(),getPriority(),getStatus(),getSize());
    }
}
