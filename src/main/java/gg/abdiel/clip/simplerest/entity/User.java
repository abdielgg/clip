package gg.abdiel.clip.simplerest.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "user")
public class User implements Cloneable, Serializable{
    private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
//  @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

}
