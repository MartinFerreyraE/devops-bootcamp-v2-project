package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Student;
import models.Teacher;
import repositories.StudentRepository;
import repositories.TeacherRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class WriteDataService {
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    public WriteDataService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public void writeStudentsDataToRepository(List<Student> students) {
        students.forEach(student -> this.studentRepository.addStudent(student));
    }

    public void writeTeacherDataToRepository(List<Teacher> teachers) {
        teachers.forEach(teacher -> this.teacherRepository.addTeacher(teacher));
    }
}
