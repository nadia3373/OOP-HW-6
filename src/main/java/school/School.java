package school;

import java.util.*;

public abstract class School {
    private static Map<Integer, Student> students = new TreeMap<>();
    private static Map<Integer, Subject> subjects = new TreeMap<>();
    protected static void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    protected static void addSubject(Subject subject) {
        subjects.put(subject.getId(), subject);
    }

    protected static void clearStudents() {
        students.clear();
    }

    protected static void clearSubjects() {
        subjects.clear();
    }

    protected static boolean containsStudent(int id) {return students.containsKey(id);}

    protected static boolean containsSubject(int id) {return subjects.containsKey(id);}

    protected static void deleteStudent(int id) {
        students.remove(id);
    }

    protected static void deleteSubject(int id) {
        subjects.remove(id);
    }

    protected static Map<Integer, Student> getAllStudents() {
        return students;
    }

    protected static Map<Integer, Subject> getAllSubjects() {
        return subjects;
    }

    protected static Student getStudent(int id) {return students.get(id);}

    protected static Subject getSubject(int id) {return subjects.get(id);}

    protected static int maxStudentId() {
        return Collections.max(students.keySet());
    }

    protected static int maxSubjectId() {
        return Collections.max(subjects.keySet());
    }

    protected static Student studentById(int id) {
        return students.get(id);
    }

    protected static Subject subjectById(int id) {
        return subjects.get(id);
    }

    protected static boolean studentsEmpty() {
        return students.isEmpty();
    }

    protected static boolean subjectsEmpty() {
        return subjects.isEmpty();
    }
}
