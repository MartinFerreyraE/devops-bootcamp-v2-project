package models;

import services.ReadDataService;

import java.util.List;

public class School {
    private String name;
    private List<Student> students;

    public School(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
