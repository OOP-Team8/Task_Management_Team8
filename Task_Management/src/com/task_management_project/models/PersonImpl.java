package com.task_management_project.models;
import com.task_management_project.models.contracts.EventLog;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.utils.contracts.DataValidation;
import com.task_management_project.utils.Validation;

import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {

    private String name;
    private List<Task> tasks = new ArrayList<>();
    private List<EventLog> history = new ArrayList<>();
    private static List<String> names = new ArrayList<>();

    public PersonImpl(String name) {
        setName(name);
        history.add(new EventLogImpl(String.format("New employee was appointed - %s",this.name)));
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        try {
//            if (names.isEmpty() || !names.contains(name)) {
//                Validation.validateStringLength(name, 5, 15, DataValidation.NAME_ERROR);
//                names.add(name);
//                this.name = name;
//            }
//            else {
//                throw new IllegalArgumentException("It was used!");
//            }
//            for (String nameItem : names) {
//                if (nameItem.equals(name)){
//                    throw new IllegalArgumentException(String.format("The name %s was used!",name));
//                }
//            }
            Validation.validateStringLength(name, 5, 15, DataValidation.NAME_ERROR);
            names.add(name);
            this.name = name;
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
    @Override
    public List<EventLog> getLogs() {
        return new ArrayList<>(history);
    }

    public static List<String> getNames() {
        return new ArrayList<>(names);
    }
}
