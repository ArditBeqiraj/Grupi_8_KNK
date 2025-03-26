package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "perdoruesi")
public class Perdoruesi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "emri", nullable = false, length = 50)
    private String emri;

    @Column(name = "mbiemri", nullable = false, length = 50)
    private String mbiemri;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "telefoni", length = 20)
    private String telefoni;

    @Column(name = "roli", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Roli roli;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "krijuar", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime krijuar;

    public enum Roli {
        DOKTOR,
        MOTER,
        ADMIN,
        PACIENT,
        PRAKTIKANT
    }

    // Getter methods
    public Integer getUserId() {
        return userId;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public Roli getRoli() {
        return roli;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getKrijuar() {
        return krijuar;
    }
}

