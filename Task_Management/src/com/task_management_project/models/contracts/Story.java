package com.task_management_project.models.contracts;

import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.Size;
import com.task_management_project.models.enums.StoryStatus;

public interface Story extends Task,Identifiable{
    Priority getPriority();
    StoryStatus getStatus();
    Size getSize();
}
