package web.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    //@NotBlank
    @Column(nullable = false)
//    @Min(value = 2, message = "First Name must be from 2 to 25 symbols")
//    @Max(value = 25, message = "First Name must be from 2 to 25 symbols")
    private String firstName;

    //@NotBlank
    @Column(nullable = false)
//    @Min(value = 2, message = "First Name must be from 2 to 25 symbols")
//    @Max(value = 25, message = "First Name must be from 2 to 25 symbols")
    private String lastName;

    //@NotNull
    @Column(nullable = false)
//    @Max(value = 120, message = "Age can' be more than 120 years")
    private byte age;

    //@NotBlank
    @Column(nullable = false)
    private String city;

    //@NotBlank
    @Column(nullable = false, unique = true)
//    @Min(value = 2, message = "First Name must be from 2 to 25 symbols")
//    @Max(value = 25, message = "First Name must be from 2 to 25 symbols")
    private String username;

//    @NotBlank
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles"
            , joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String firstName, String lastName, byte age, String city, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.username = username;
        this.password = password;
    }

    public void addRoleToUser(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User's Details:" +
                "\nFirstName: " + firstName +
                "\nLast Name: " + lastName +
                "\nAge: " + age +
                "\nCity: " + city +
                "\nUsername: " + username +
                "\nRoles: " + roles;
    }
}
