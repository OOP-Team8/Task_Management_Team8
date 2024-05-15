package com.task_management_project.models.contracts;

import com.task_management_project.models.BoardImpl;
import com.task_management_project.models.PersonImpl;

import java.util.List;

public interface Team {

    String getName();

    List<Person> getMembers();

    List<Board> getBoards();

    void addMember(Person person);

    void addBoard(Board board);
}
