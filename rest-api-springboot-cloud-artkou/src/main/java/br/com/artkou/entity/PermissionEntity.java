package br.com.artkou.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Data
@Table(name = "permission")
public class PermissionEntity implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    public PermissionEntity() {}

    @Override
    public String getAuthority() {
        return this.description;
    }
}
