package com.domain.musicplatform.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserDTO {
    @Id
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Date registrationDate;
    private Date lastLoginDate;

}
