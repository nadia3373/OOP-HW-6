package phonebook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Contact {
    static int counter = 1;
    private int id;
    private String name;
    private ArrayList<Number> phoneNumbers;

    protected Contact(String name) {
        this.id = counter;
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
    }

    protected void addPhoneNumber(Number num) {
        phoneNumbers.add(num != null ? num : new Number());
    }

    protected void removePhoneNumber(Number num) {
        phoneNumbers.remove(num);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(this.id).append("\n")
                .append("Name: ").append(this.name).append("\n")
                .append("Phone numbers: ").append("\n");
        for (Number number : this.phoneNumbers) {
            sb.append(number.getType()).append(" - ").append(number.getNumber()).append("\n");
        }
        return sb.toString();
    }
}
