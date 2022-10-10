package models;

import helpers.Grade;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String code;
    private String name;
    private List<Subject> subjectList;

    public Teacher(String name) {
        this.code = "TE" + (int)(Math.random() * 100 + 1) + name;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
