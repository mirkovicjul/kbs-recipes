package beans;

import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends Model {

    @Id
    @Column
    Long id;

    @Column
    String username;

    @Column
    String email;

    @Column
    String password;

    @Column
    Long userType;

    public User(Long id, String username, String email, String password, Long userType) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
}
