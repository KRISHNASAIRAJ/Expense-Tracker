package com.devops.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder
/*
The Builder pattern is a design pattern that simplifies object creation by allowing you to construct objects step-by-step in a readable, flexible, and maintainable way.
Key Benefits:
✔ Improves readability (no need to remember constructor parameter order).
✔ Avoids multiple constructors (no telescoping constructor problem).
✔ Allows flexible object creation (set only required fields).
✔ Supports immutability (works well with final fields).
 */
public class User {
    @Id
    private String emailId;

    @Column(nullable = false)
    private String hashedPassword;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phone;

}
