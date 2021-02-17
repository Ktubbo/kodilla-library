package com.library.loan.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.copy.domain.Copy;
import com.library.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="loans")
@Component
public class Loan {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "copy")
    private Copy copy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "borrowingDate")
    private Date borrowingDate;

    @Column(name = "returningDate")
    private Date returningDate;

    public Loan(Copy copy, User user) {
        this.copy = copy;
        this.user = user;
    }
}
