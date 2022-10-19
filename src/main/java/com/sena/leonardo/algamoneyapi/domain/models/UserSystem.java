package com.sena.leonardo.algamoneyapi.domain.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserSystem {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission", joinColumns = @JoinColumn(name = "id_user")
    , inverseJoinColumns = @JoinColumn(name = "id_permission"))
    private List<Permission> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSystem userSystem = (UserSystem) o;

        return id.equals(userSystem.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
