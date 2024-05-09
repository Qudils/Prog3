package application;

public class Segreteria {
    public String username;
    public String password;
   
    // Costruttore vuoto
    public Segreteria() {
    }

    // Costruttore con parametri per inizializzare username e password
    public Segreteria(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
