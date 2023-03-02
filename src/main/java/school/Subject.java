package school;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Subject {
    static int counter = 1;
    private int id;
    private String name;

    protected Subject(String name) {
        this.id = counter;
        this.name = name;
    }
}
