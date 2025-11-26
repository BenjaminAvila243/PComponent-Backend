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
@Table(name = "venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "metodo_pago_id")
    private Metodopago metodoPago;

    @ManyToOne
    @JoinColumn(name = "metodo_envio_id")
    private Metodoenvio metodoEnvio;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @Column(nullable = false)
    private String fecha;
    
}
