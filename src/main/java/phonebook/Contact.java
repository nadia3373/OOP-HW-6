package phonebook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Contact {
    private static int counter = 1;
    private int id;
    private String name;
    private List<Number> phoneNumbers;

    public Contact(String name, String label, String phone) {
        this.id = counter;
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
        addPhoneNumber(null);
        counter++;
    }

    public Contact(int id, String name, String label, String phone) {
        this.id = id;
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
        addPhoneNumber(null);
    }

    public Contact(String name) {
        this.id = counter;
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
        counter++;
    }

    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
    }

    protected void addPhoneNumber(Number num) {
        if (num != null) {
            phoneNumbers.add(num);
        } else {
            phoneNumbers.add(new Number());
        }
    }

    protected Number getPhoneNumber(int id) {
        return phoneNumbers.get(id);
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
