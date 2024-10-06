package com.bilalbererk.Dukkan.models;

import com.bilalbererk.Dukkan.utils.UserRoles;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "_user")
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="user_seq_gen"
    )
    @SequenceGenerator(
            name = "user_seq_gen",
            sequenceName = "user_id_gen",
            allocationSize = 1
    )
    private Integer id;
    @Column(
            name= "user_name",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;
    @Column(
            name = "password",
            nullable = false
    )
    private String password;
    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private UserRoles userRole;

    @Column(
            name = "phone_number",
            unique = true
    )
    private String phoneNumber;
    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @ManyToMany
    @JoinTable(
            name = "address_user_table",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name ="address_id")
    )
    private Set<Address> Addresses;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.ALL},  fetch = FetchType.EAGER)
    List<Shop> shop;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
