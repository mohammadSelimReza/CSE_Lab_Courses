import java.io.Serializable;

public class Booking implements Serializable {
    private String name;
    private String email;
    private String vaccineId;
    private String vaccineName;

    public Booking(String name, String email, String vaccineId, String vaccineName) {
        this.name = name;
        this.email = email;
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", vaccineId='" + vaccineId + '\'' +
                ", vaccineName='" + vaccineName + '\'' +
                '}';
    }
}
