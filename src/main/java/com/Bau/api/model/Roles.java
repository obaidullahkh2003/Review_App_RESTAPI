package com.Bau.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "userroles",joinColumns = @JoinColumn(name = "User_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "Role_id",referencedColumnName = "id"))
    private List<Roles> roles=new ArrayList<>();
}
