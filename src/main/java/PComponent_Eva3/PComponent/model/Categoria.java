package PComponent_Eva3.PComponent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombreCategoria", length = 50, nullable = false)
    private String nombreCategoria;    

    @Column(name = "descripcion", length = 150, nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_padre_id")
    private Categoria categoriaPadre;

}
