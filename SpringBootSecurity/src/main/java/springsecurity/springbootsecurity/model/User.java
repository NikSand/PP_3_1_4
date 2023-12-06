package springsecurity.springbootsecurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "second_name")
    @NotBlank(message = "Фамилия не должна быть пустой")
    @Pattern(regexp = "^[^\\d]*$", message = "В поле не должно быть цифр")
    @Size(min = 1, max = 50, message = "Фамилия не должна быть больше 0, но меньше символов 50")
    private String lastname;

    @Column(name = "age")
    @NotNull(message = "Возраст не должен быть пустым")
    @Min(value = 0, message = "Возраст не может быть меньше 0")
    @Max(value = 100, message = "Возраст не может быть больше 100")
    private Byte age;

    @Column(name = "e_mail")
    @NotBlank(message = "Почта не может быть пустой!")
    @Size(min = 1, max = 50, message = "Длина больше 0 символов, но меньше 50")
    private String email;

    @Column(name = "first_name", unique = true)
    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Длина больше 0 символов, но меньше 50")
    private String firstname;

    @Column(name = "password")
    @NotBlank(message = "Поле не может быть пустым")
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String firstname, String lastname, Byte age, String email, String password, Set<Role> roles) {
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.firstname = firstname;
        this.password = password;
        this.roles = roles;
    }


    public User() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }



    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return firstname;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)  && Objects.equals(lastname, user.lastname) && Objects.equals(age, user.age) && Objects.equals(email, user.email) && Objects.equals(firstname, user.firstname) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, age, email, firstname, password, roles);
    }

    public String getRolesToString() {
        return roles.stream().map(Role::toString).collect(Collectors.joining(" "));
    }
}