package com.example.demo.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Nidhal on 13/03/2019.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String role;
}
