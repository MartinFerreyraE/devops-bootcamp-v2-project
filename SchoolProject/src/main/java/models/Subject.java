package models;

import helpers.Grade;

public class Subject {
    private String name;
    private Teacher teacher;
    private Grade grade;

    private String code;

    private String subject;

    public Subject(String name, Teacher teacher, Grade grade) {
        this.name = name;
        this.teacher = teacher;
        this.grade = grade;
    }
    public String getName(){return this.name;}

    public Grade getGrade(){return this.grade;}

    public String getSubject(){return this.subject;}

    public String getCode() {
        return code;
    }

}
