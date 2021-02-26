package com.demo.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class User {

    private long id;

    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    private Date dateOfBirth;

}
