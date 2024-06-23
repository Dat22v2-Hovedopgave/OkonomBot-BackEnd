package com.example.okonombotbackend.security.entity;


import com.example.okonombotbackend.backend.dto.UserRequest;
import com.example.okonombotbackend.security.dto.UserWithRolesRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`user`")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR_TYPE")
public class User implements UserDetails {

    @Transient
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Id
    @Column(nullable = false,length = 50,unique = true)
    String username;

    @Column(nullable = false,length = 50,unique = true)
    String email;

    //60 = length of a bcrypt encoded password
    @Column(nullable = false, length = 60)
    String password;

    private boolean enabled= true;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime edited;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('USER','ADMIN')")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "security_role")
    List<Role> roles = new ArrayList<>();


   // We will use this constructor when/if users must be created via an HTTP-request
    public User(UserWithRolesRequest body) {
        this.username = body.getUsername();
        this.setPassword(body.getPassword());
        this.email = body.getEmail();
    }

    public User(String user, String password, String email){
        this.username = user;
        setPassword(password);
        this.email = email;
    }

    public User(UserRequest userRequest) {
        this.username = userRequest.getUsername();
        this.email = userRequest.getEmail();
        setPassword(userRequest.getPassword());
    }

    public void setPassword(String pw){
        this.password = passwordEncoder.encode(pw);
    }

    public void addRole(Role role){
        roles.add(role);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
    }

    //You can, but are NOT expected to use the fields below
    @Override
    public boolean isAccountNonExpired() {return enabled;}

    @Override
    public boolean isAccountNonLocked() { return enabled;}

    @Override
    public boolean isCredentialsNonExpired() { return enabled; }
}

