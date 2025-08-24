package dev.rdkln.tracker.user.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @Embedded
    private UserId id;

    private String name;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public static enum UserRole{
        USER,ADMIN
    }
}
