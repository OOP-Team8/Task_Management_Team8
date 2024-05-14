package com.task_management_project.models.contracts;

import com.task_management_project.models.BoardImpl;
import com.task_management_project.models.PersonImpl;

import java.util.List;

public interface Team {

    String getName();

    List<PersonImpl> getMembers();

    List<BoardImpl>  getBoards();
}
