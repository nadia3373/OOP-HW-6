package school;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Grade {
    String subjectName;
    int value;
    protected Grade(String subjectName, int value) {
        this.subjectName = subjectName;
        this.value = value;
    }
}
