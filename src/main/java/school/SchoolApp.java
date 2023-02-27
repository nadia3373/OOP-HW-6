package school;

import utilities.*;

import java.util.Collection;

public class SchoolApp extends SubApp {
    private String studentSource, studentResult, subjectSource, subjectResult;
    public SchoolApp(MainApp app) {
        super(app, """
                        Выберите действие:
                        1. Импорт данных
                        2. Добавить ученика
                        3. Добавить предмет
                        4. Удалить ученика
                        5. Удалить предмет
                        6. Отобразить всех учеников
                        7. Отобразить информацию об ученике
                        8. Очистить информационную систему
                        9. Добавить оценку
                        10. Экспорт данных
                        0. Выход
                                            
                        Введите число:\s""");
        studentSource = "src/main/java/school/sourceStudent.json";
        studentResult = "src/main/java/school/resultStudent.json";
        subjectSource = "src/main/java/school/sourceSubject.json";
        subjectResult = "src/main/java/school/resultSubject.json";
    }

    private void addGrade() {
        Student student = null;
        Subject subject = null;
        if (studentChoices()) {
            int studChoice = ConsoleIO.intInput("Введите id студента: ");
            if (School.containsStudent(studChoice)) {
                student = School.getStudent(studChoice);
            } else {
                ConsoleIO.error("Некорректный выбор.");
            }
        }
        if (subjectChoices()) {
            int subjChoice = ConsoleIO.intInput("Введите id предмета: ");
            if (School.containsSubject(subjChoice)) {
                subject = School.getSubject(subjChoice);
            } else {
                ConsoleIO.error("Некорректный выбор.");
            }
        }
        if (student != null && subject != null) {
            student.addGrade(new Grade(subject.getName(), ConsoleIO.intInput("Введите оценку: ")));
            ConsoleIO.success("Оценка успешно добавлена.");
        }
    }

    private void addStudent() {
        String name = ConsoleIO.stringInput("Введите имя ученика: ");
        String className = ConsoleIO.stringInput("Введите класс ученика: ");
        Student student = new Student(className, name);
        School.addStudent(student);
        ConsoleIO.success("Ученик успешно добавлен");
    }

    private void addSubject() {
        String name = ConsoleIO.stringInput("Введите название предмета: ");
        Subject subject = new Subject(name);
        School.addSubject(subject);
        ConsoleIO.success("Предмет успешно добавлен");
    }

    private void clear() {
        School.clearStudents();
        School.clearSubjects();
        ConsoleIO.success("Все ученики и предметы удалены.");
    }

    protected void option(int choice) {
        switch (choice) {
            case 1 -> read();
            case 2 -> addStudent();
            case 3 -> addSubject();
            case 4 -> removeStudent();
            case 5 -> removeSubject();
            case 6 -> printAllStudents();
            case 7 -> printStudent();
            case 8 -> clear();
            case 9 -> addGrade();
            case 10 -> save();
            case 0 -> ConsoleIO.info("Главное меню.");
            default -> ConsoleIO.error("Некорректный выбор.");
        }
    }

    private void printAllStudents() {
        if (studentsEmpty()) {
            School.getAllStudents().values().forEach(student -> ConsoleIO.info(student.toString()));
        }
    }

    private void printStudent() {
        if (studentChoices()) {
            int choice = ConsoleIO.intInput("Введите id ученика: ");
            if (School.containsStudent(choice)) {
                Student student = School.getAllStudents().get(choice);
                ConsoleIO.info(student.toString());
            } else {
                ConsoleIO.error("Некорректный выбор");
            }
        }
    }

    protected void read() {
        Serializer<Student> studentSerializer = new JsonSerializer<>();
        FileManager<Student> studentFileManager = new FileManager<>();
        Serializer<Subject> subjectSerializer = new JsonSerializer<>();
        FileManager<Subject> subjectFileManager = new FileManager<>();
        try {
            Collection<Student> students = studentFileManager.read(studentSource, studentSerializer, Student.class);
            students.forEach(School::addStudent);
            Collection<Subject> subjects = subjectFileManager.read(subjectSource, subjectSerializer, Subject.class);
            subjects.forEach(School::addSubject);
            ConsoleIO.success("Данные об учениках и предметах успешно загружены");
        } catch (Exception e) {
            e.printStackTrace();
            ConsoleIO.error("Произошла ошибка при чтении файла");
        }
    }

    private void removeStudent(){
        if (studentChoices()) {
            int choice = ConsoleIO.intInput("Введите id ученика: ");
            if (School.containsStudent(choice)) {
                School.deleteStudent(choice);
                ConsoleIO.success("Ученик успешно удалён.");
            } else {
                ConsoleIO.error("Некорректный выбор");
            }
        }
    }

    private void removeSubject(){
        if (subjectChoices()) {
            int choice = ConsoleIO.intInput("Введите id предмета: ");
            if (School.containsSubject(choice)) {
                School.deleteSubject(choice);
                ConsoleIO.success("Предмет успешно удалён.");
            } else {
                ConsoleIO.error("Некорректный выбор");
            }
        }
    }

    protected void save() {
        Collection<Student> students = School.getAllStudents().values();
        Collection<Subject> subjects = School.getAllSubjects().values();
        Serializer<Student> studentSerializer = new JsonSerializer<>();
        FileManager<Student> studentFileManager = new FileManager<>();
        Serializer<Subject> subjectSerializer = new JsonSerializer<>();
        FileManager<Subject> subjectFileManager = new FileManager<>();
        try {
            studentFileManager.write(studentResult, students, studentSerializer);
            subjectFileManager.write(subjectResult, subjects, subjectSerializer);
            ConsoleIO.success("Результат успешно записан в файлы");
        } catch (Exception e) {
            e.printStackTrace();
            ConsoleIO.error("Произошла ошибка при записи в файлы");
        }
    }

    private boolean studentChoices() {
        if (studentsEmpty()) {
            ConsoleIO.info("Ученики:");
            School.getAllStudents().values().forEach(student -> ConsoleIO.info(String.format("%d. %s", student.getId(), student.getName())));
            return true;
        }
        return false;
    }

    private boolean subjectChoices() {
        if (subjectsEmpty()) {
            ConsoleIO.info("Предметы:");
            School.getAllSubjects().values().forEach(subject -> ConsoleIO.info(String.format("%d. %s", subject.getId(), subject.getName())));
            return true;
        }
        return false;
    }

    private boolean studentsEmpty() {
        if (School.studentsEmpty()) {
            ConsoleIO.error("Нет учеников.");
            return false;
        }
        return true;
    }

    private boolean subjectsEmpty() {
        if (School.subjectsEmpty()) {
            ConsoleIO.error("Нет предметов.");
            return false;
        }
        return true;
    }
}