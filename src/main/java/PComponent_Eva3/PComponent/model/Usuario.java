package PComponent_Eva3.PComponent.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40, nullable = false)
    private String nombre;

    @Column(length = 40, nullable = false)
    private String apellido;

    @Column(length = 12, nullable = false, unique = true)
    private String rut;

    @Column(length = 20, nullable = false)
    private Integer telefono;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contraseña;

    @ManyToOne
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_rol",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();
}
