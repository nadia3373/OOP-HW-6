package school;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Subject {
    public static int counter = 1;
    private int id;
    private String name;

    public Subject(String name) {
        this.id = School.subjectsEmpty() ? counter++ : School.maxSubjectId() + 1;
        this.name = name;
    }

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
