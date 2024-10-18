package org.dmle.userfood.domain;

import java.util.Objects;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private Long dob;
    private Long createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Long getDob() {
        return dob;
    }

    public void setDob(long dob) {
        this.dob = dob;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", createdAt=" + createdAt +
                '}';
    }
}
