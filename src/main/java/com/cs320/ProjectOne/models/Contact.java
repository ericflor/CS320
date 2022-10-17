package com.cs320.ProjectOne.models;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(max = 10)
    @NotNull
    private String firstName;
    @Size(max = 10)
    @NotNull
    private String lastName;
    @NotNull
    @Size(min = 10, max = 10)
    private String phoneNumber;
    @Size(max = 30)
    @NotNull
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Contact contact = (Contact) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
