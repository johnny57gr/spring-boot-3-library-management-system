package com.ypassas.springboot.librarymanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "members")
public class Member {

    // define fields
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Loan> loans;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "is required")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "is required")
    @Column(name = "lastName")
    private String lastName;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^(\\+30|0030)?\\d{10}$", message = "must be valid GR phone number")
    @Column(name = "phone", unique = true)
    private String phone;

    @Email(message = "invalid email")
    @NotBlank(message = "is required")
    @Column(name = "email", unique = true)
    private String email;

    @NotNull(message = "is required")
    @PastOrPresent(message = "must be today or a past date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "membershipDate")
    private LocalDate membershipDate;

    @NotNull(message = "is required")
    @Past(message = "must be a past date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateOfBirthday")
    private LocalDate dateOfBirthday;

    // define constructors

    public Member() {

    }

    public Member(String firstName, String lastName, String phone, String email, LocalDate membershipDate, LocalDate dateOfBirthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.membershipDate = membershipDate;
        this.dateOfBirthday = dateOfBirthday;
    }

    // define getters/setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    // define toString
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", membershipDate=" + membershipDate +
                ", dateOfBirthday=" + dateOfBirthday +
                '}';
    }
}
