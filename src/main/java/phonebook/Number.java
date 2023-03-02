package phonebook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Number {
    private String type;
    private String number;

    protected Number(String type, String number) {
        this.type = type;
        this.number = number;
    }
}
