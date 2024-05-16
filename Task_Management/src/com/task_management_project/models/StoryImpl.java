package com.task_management_project.models;

import com.task_management_project.models.contracts.Story;

public class StoryImpl extends TaskImpl implements Story {
    public StoryImpl(int id, String title, String description) {
        super(id, title, description);
    }

    @Override
    public String getAsString() {
        return null;
    }
}
