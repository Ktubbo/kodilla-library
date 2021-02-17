package com.library.loan.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.copy.domain.Copy;
import com.library.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class LoanDto {

    private Long id;
    @JsonProperty("copyId")
    private Copy copy;
    @JsonProperty("userId")
    private User user;
    private Date borrowingDate;
    private Date returningDate;
}
