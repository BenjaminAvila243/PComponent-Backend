package PComponent_Eva3.PComponent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "metodoenvio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metodoenvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipoEnvio", length = 50, nullable = false)
    private String tipoEnvio;

    @Column(name = "costo", nullable = false)
    private Integer costo;

    @Column(name = "tiempoEntrega", length = 50, nullable = false)
    private String tiempoEntrega;

    @Column(name = "direccion", length = 150, nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = false)
    private Integer telefono;

    @Column(name = "correo", length = 150, nullable = false)
    private String correo;

    @Column(name = "venta_id", nullable = false)
    private Integer idVenta;

    @Column(name = "nombre", nullable = false)
    private String nombre;
}
