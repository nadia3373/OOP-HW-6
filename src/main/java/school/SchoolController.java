package school;

import utilities.*;

import java.util.Collection;

public class SchoolController implements Controller {
    private final SchoolView view = new SchoolView();
    private final SchoolModel model = new SchoolModel();
    private String studentSource = "src/main/java/school/sourceStudent.json";
    private String studentResult = "src/main/java/school/resultStudent.json";
    private String subjectSource = "src/main/java/school/sourceSubject.json";
    private String subjectResult = "src/main/java/school/resultSubject.json";
    private String menu = """
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

            Введите число:\s""";

    private void addGrade() {
        Student student = null;
        Subject subject = null;
        if (!model.studentsEmpty() && !model.subjectsEmpty()) {
            view.printStudents(model.getAllStudents().values());
            int studChoice = view.getInt("Введите id студента: ");
            student = model.getStudent(studChoice);
            view.printSubjects(model.getAllSubjects().values());
            int subjChoice = view.getInt("Введите id предмета: ");
            subject = model.getSubject(subjChoice);
        } else {
            view.error("Оценки, либо предметы пусты");
        }
        if (student != null && subject != null) {
            model.addGrade(student, new Grade(subject.getName(), view.getInt("Введите оценку: ")));
            view.success("Оценка успешно добавлена.");
        } else {
            view.error("Некорректный выбор.");
        }
    }

    private void addStudent() {
        model.addStudent(view.getStudent());
        ConsoleIO.success("Ученик успешно добавлен");
    }

    private void addSubject() {
        model.addSubject(view.getSubject());
        ConsoleIO.success("Предмет успешно добавлен");
    }

    private void clear() {
        model.clearStudents();
        model.clearSubjects();
        ConsoleIO.success("Все ученики и предметы удалены.");
    }

    @Override
    public void exit() {
        view.info("Главное меню");
    }

    public void option(int choice) {
        switch (choice) {
            case 1 -> read();
            case 2 -> addStudent();
            case 3 -> addSubject();
            case 4 -> removeStudent();
            case 5 -> removeSubject();
            case 6 -> view.printStudents(model.getAllStudents().values());
            case 7 -> printStudent();
            case 8 -> clear();
            case 9 -> addGrade();
            case 10 -> save();
            case 0 -> exit();
            default -> view.error("Некорректный выбор.");
        }
    }

    private void printStudent() {
        if (!model.studentsEmpty()) {
            view.printStudents(model.getAllStudents().values());
            int choice = ConsoleIO.intInput("Введите id ученика: ");
            Student student = model.getStudent(choice);
            if (student != null) {
                view.printStudents((Collection<Student>) student);
            } else {
                view.error("Некорректный выбор.");
            }
        } else {
            view.error("Нет учеников.");
        }
    }

    protected void read() {
        Serializer<Student> studentSerializer = new JsonSerializer<>();
        FileManager<Student> studentFileManager = new FileManager<>();
        Serializer<Subject> subjectSerializer = new JsonSerializer<>();
        FileManager<Subject> subjectFileManager = new FileManager<>();
        try {
            Collection<Student> students = studentFileManager.read(studentSource, studentSerializer, Student.class);
            students.forEach(model::addStudent);
            Collection<Subject> subjects = subjectFileManager.read(subjectSource, subjectSerializer, Subject.class);
            subjects.forEach(model::addSubject);
            view.success("Данные об учениках и предметах успешно загружены");
        } catch (Exception e) {
            e.printStackTrace();
            view.error("Произошла ошибка при чтении файла");
        }
    }

    private void removeStudent(){
        if (!model.studentsEmpty()) {
            view.printStudents(model.getAllStudents().values());
            int choice = view.getInt("Введите id ученика: ");
            if (model.containsStudent(choice)) {
                model.deleteStudent(choice);
                view.success("Ученик успешно удалён.");
            } else {
                view.error("Некорректный выбор");
            }
        } else {
            view.error("Нет учеников.");
        }
    }

    private void removeSubject(){
        if (!model.subjectsEmpty()) {
            int choice = view.getInt("Введите id предмета: ");
            if (model.containsSubject(choice)) {
                model.deleteSubject(choice);
                view.success("Предмет успешно удалён.");
            } else {
                view.error("Некорректный выбор");
            }
        } else {
            view.error("Нет предметов.");
        }
    }

    protected void save() {
        Collection<Student> students = model.getAllStudents().values();
        Collection<Subject> subjects = model.getAllSubjects().values();
        Serializer<Student> studentSerializer = new JsonSerializer<>();
        FileManager<Student> studentFileManager = new FileManager<>();
        Serializer<Subject> subjectSerializer = new JsonSerializer<>();
        FileManager<Subject> subjectFileManager = new FileManager<>();
        try {
            studentFileManager.write(studentResult, students, studentSerializer);
            subjectFileManager.write(subjectResult, subjects, subjectSerializer);
            view.success("Результат успешно записан в файлы");
        } catch (Exception e) {
            e.printStackTrace();
            view.error("Произошла ошибка при записи в файлы");
        }
    }

    public void start() {
        int choice;
        do {
            choice = ConsoleIO.intInput(menu);
            option(choice);
        } while (choice != 0);
    }
}