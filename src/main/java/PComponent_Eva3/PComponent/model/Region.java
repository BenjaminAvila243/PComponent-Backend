package PComponent_Eva3.PComponent.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Region")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40, nullable = false)
    private String nombreRegion;

}
