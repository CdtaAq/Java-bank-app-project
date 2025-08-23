// SAVE: banking-project/src/main/java/com/bank/entity/Role.java
package com.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "roles",
       uniqueConstraints = {@UniqueConstraint(name = "uk_role_name", columnNames = "name")})
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min=2, max=32)
    @Column(nullable=false, length=32)
    private String name;

    @Size(max=255)
    private String description;

    public Role() {}
    public Role(String name, String description) {
        this.name = name; this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name != null ? name.trim() : null; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
