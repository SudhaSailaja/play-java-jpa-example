package models;

import javax.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;

    public String emailID;

    public String password;

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
