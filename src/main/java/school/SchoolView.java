package school;

import utilities.ConsoleIO;
import utilities.View;

import java.util.Collection;

public class SchoolView implements View {
    protected int getInt(String message) {
        return ConsoleIO.intInput(message);
    }

    protected Student getStudent() {
        String name = ConsoleIO.stringInput("Введите имя ученика: ");
        String className = ConsoleIO.stringInput("Введите класс ученика: ");
        return new Student(className, name);
    }

    protected Subject getSubject() {
        return new Subject(ConsoleIO.stringInput("Введите название предмета: "));
    }

    protected void printStudents(Collection<Student> students) {
        students.forEach(student -> ConsoleIO.info(String.format("%d. %s", student.getId(), student.getName())));
    }

    protected void printSubjects(Collection<Subject> subjects) {
        subjects.forEach(subject -> ConsoleIO.info(String.format("%d. %s", subject.getId(), subject.getName())));
    }
}
