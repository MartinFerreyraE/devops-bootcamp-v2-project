import helpers.Grade;
import models.School;
import models.Student;
import repositories.StudentRepository;
import repositories.TeacherRepository;
import services.AccessValidator;
import services.ReadDataService;
import services.WriteDataService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();
        TeacherRepository teacherRepository = new TeacherRepository();

        ReadDataService readDataService = new ReadDataService();
        var students = readDataService.readStudentsDataFromJson();
        WriteDataService writeDataService = new WriteDataService(studentRepository, teacherRepository);
        writeDataService.writeStudentsDataToRepository(students);

        var teachers = readDataService.readTeachersDataFromJson();
        writeDataService.writeTeacherDataToRepository(teachers);

        Scanner scanner = new Scanner(System.in);
        var school = new School("American School");

        System.out.println("=============================");
        System.out.println("Welcome to " + school.getName());
        System.out.println("=============================");

        System.out.println("Select your role please: ");
        System.out.println("1. Teacher");
        System.out.println("2. Student");
        System.out.println("3. Exit");

        String option = scanner.nextLine();
        boolean access = false;

        while (!access) {
            System.out.println("Please enter your code");
            AccessValidator accessValidator = new AccessValidator(studentRepository, teacherRepository);

            String code = scanner.nextLine();

            switch (option) {
                case "1":
                    var teacher = accessValidator.verifyTeacherAccess(code);
                    if (teacher != null) {
                        access = true;
                        System.out.println("Select your  option please: ");
                        System.out.println("1. List of Students of Elementary School grade");
                        System.out.println("2. Enter a grade for a student");
                        String optionTeacher = scanner.nextLine();
                        switch (optionTeacher) {
                            case "1":
                                try {
                                    students = studentRepository.getAllStudents();
                                    for (Student student : students) {
                                        System.out.println(student.getCode() + " - " + student.getName() + " - " + student.getAge() + " - " + student.getGrade() + " - " + student.getScore());
                                    }

                                } catch (Exception er){
                                    System.out.println(er.getMessage());
                                }break;

                                case "2":
                                    System.out.println("Students:");
                                    students = studentRepository.getAllStudents();
                                    for (Student student : students) {
                                        System.out.println(student.getCode());
                                    }
                                    System.out.println("Enter the code of the student");
                                    String studentCode = scanner.nextLine();
                                    var student = studentRepository.getStudentByCode(studentCode);
                                    System.out.println("Set the grade: \n1. Elementary School, 2. Middle School, 3. High School");
                                    String goption = scanner.nextLine();
                                    switch (goption) {
                                        case "1" -> student.setGrade(Grade.ELEMENTARY_SCHOOL);
                                        case "2" -> student.setGrade(Grade.MIDDLE_SCHOOL);
                                        case "3" -> student.setGrade(Grade.HIGH_SCHOOL);
                                    }
                                    System.out.println(student.getCode()+" - "+student.getName()+" - "+student.getAge()+" - "+student.getGrade()+" - "+student.getScore());
                                    break;
                        }

                    }
                case "2":
                    var student = accessValidator.verifyStudentAccess(code);
                    if (student != null) {
                        access = true;
                        students = studentRepository.readStudentsByCode(scanner.nextLine());
                        System.out.println(student.getCode()+" - "+student.getName()+" - "+student.getAge()+" - "+student.getGrade()+" - "+student.getScore());

                    }
                        break;
                    case "3":
                    System.exit(0);
                    break;
                    }

            }

        }
    }

