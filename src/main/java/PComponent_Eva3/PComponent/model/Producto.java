package PComponent_Eva3.PComponent.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  
    
    @Column(name = "nombreProducto", nullable = false, length = 30)
    private String nombre;

    @Column(name = "precioProducto", nullable = false)
    private Integer precio;
    
    @Column(name = "imagenProducto", nullable = true)
    private String imagenUrl;

    @Column(name = "descripcionProducto", nullable = false)
    private String descripcion;

    @Column(name = "stockProducto",nullable = true)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable=false)
    private Marca marca;

}

