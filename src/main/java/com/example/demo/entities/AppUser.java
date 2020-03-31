package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nidhal on 13/03/2019.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    @Email
    String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) @NotNull
    String password;
    @URL
    String avatarURL;
    @ManyToMany(fetch = FetchType.EAGER)
    List<AppRole> roles = new ArrayList<>();
}
