import java.io.*;
import java.util.*;
import javax.swing.*;

class Doctor implements Serializable {
    private String username;
    private String password;

    public Doctor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
