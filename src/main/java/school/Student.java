package school;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Student {
    public static int counter = 1;
    private int id;
    private String className, name;
    private ArrayList<Grade> grades;

    public Student(String className, String name) {
        this.id = School.studentsEmpty() ? counter++ : School.maxStudentId() + 1;
        this.className = className;
        this.name = name;
        this.grades = new ArrayList<Grade>();
    }

    public Student(int id, String className, String name) {
        this.id = id;
        this.className = className;
        this.name = name;
        this.grades = new ArrayList<Grade>();
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public void removeGrade(Grade grade) {
        grades.remove(grade);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(this.id).append("\n")
                .append("Name: ").append(this.name).append("\n")
                .append("Class: ").append(this.className).append("\n")
                .append("Grades: ").append("\n");
        double sum = 0;
        for (Grade grade : this.grades) {
            sb.append(grade.getSubjectName()).append(" - ").append(grade.getValue()).append("\n");
            sum += grade.getValue();
        }
        sb.append("Average rating: ").append(grades.size() > 0 ? sum / grades.size() : 0).append("\n");
        return sb.toString();
    }
}
