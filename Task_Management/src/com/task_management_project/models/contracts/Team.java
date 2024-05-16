package com.task_management_project.models.contracts;

import java.util.List;

public interface Team extends Nameable {

    List<Person> getMembers();

    List<Board> getBoards();

    void addMember(Person person);

    void addBoard(Board board);


}
