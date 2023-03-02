package school;

import java.util.Map;
import java.util.TreeMap;

public class SchoolModel {
    private Map<Integer, Student> students = new TreeMap<>();
    private Map<Integer, Subject> subjects = new TreeMap<>();

    protected void addGrade(Student student, Grade grade) {
        student.addGrade(grade);
    }
    protected void addStudent(Student student) {
        students.put(student.getId(), student);
        Student.counter++;
    }

    protected void addSubject(Subject subject) {
        subjects.put(subject.getId(), subject);
        Subject.counter++;
    }

    protected void clearStudents() {
        students.clear();
    }

    protected void clearSubjects() {
        subjects.clear();
    }

    protected boolean containsStudent(int id) {return students.containsKey(id);}

    protected boolean containsSubject(int id) {return subjects.containsKey(id);}

    protected void deleteStudent(int id) {
        students.remove(id);
    }

    protected void deleteSubject(int id) {
        subjects.remove(id);
    }

    protected Map<Integer, Student> getAllStudents() {
        return students;
    }

    protected Map<Integer, Subject> getAllSubjects() {
        return subjects;
    }

    protected Student getStudent(int id) {return students.getOrDefault(id, null);}

    protected Subject getSubject(int id) {return subjects.getOrDefault(id, null);}

    protected boolean studentsEmpty() {
        return students.isEmpty();
    }

    protected boolean subjectsEmpty() {
        return subjects.isEmpty();
    }
}
