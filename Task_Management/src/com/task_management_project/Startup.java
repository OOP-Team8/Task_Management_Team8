package com.task_management_project;

import com.task_management_project.core.TaskManagementEngineImpl;
import com.task_management_project.models.PersonImpl;
import com.task_management_project.models.contracts.Person;

public class Startup {
    public static void main(String[] args) {
        TaskManagementEngineImpl engine = new TaskManagementEngineImpl();
        engine.start();

//        Person person = new PersonImpl("Ivan123");
//        Person person1 = new PersonImpl("Ivan123");
//        Person person2 = new PersonImpl("Pesho123");
//        Person person3 = new PersonImpl("Pesho123");
//        Person person4 = new PersonImpl("Dimitar");
//
//        for (String name : PersonImpl.getNames()) {
//            System.out.println(name);
//        }
    }
}