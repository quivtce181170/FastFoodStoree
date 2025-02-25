/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Jackt
 */
public class Account2 {
    private String id;
    private String name;
    private String password;

    public Account2() {}

    public Account2(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    // Getter và Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Phương thức xác minh tài khoản
    public boolean verifyAccount(String username, String password) {
        return this.name.equals(username) && this.password.equals(password);
    }
}