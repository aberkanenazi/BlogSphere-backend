package com.aberkane.blogsphere.blogsphere_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;
    private String password;
    private boolean locked;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private UserRoles role;
    private String phoneNumber;
    @OneToMany(mappedBy = "author")
    private List<Post> posts;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;


}
