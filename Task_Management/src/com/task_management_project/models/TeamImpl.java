package com.task_management_project.models;

import com.task_management_project.models.contracts.Board;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Team;
import com.task_management_project.utils.contracts.DataValidation;
import com.task_management_project.utils.Validation;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    private String name;
    private List<Person> members = new ArrayList();
    private List<Board> boards = new ArrayList();

    public TeamImpl(String name) {
        this.setName(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        Validation.validateStringLength(name, 5, 15, DataValidation.NAME_ERROR);
        this.name = name;
    }

    public List<Person> getMembers() {
        return new ArrayList(this.members);
    }

    public List<Board> getBoards() {
        return new ArrayList(this.boards);
    }

    public void addMember(Person person) {
        if (!this.members.isEmpty() && this.members.contains(person)) {
            throw new IllegalArgumentException();
        } else {
            this.members.add(person);
        }
    }

    public void addBoard(Board board) {
    }
}
