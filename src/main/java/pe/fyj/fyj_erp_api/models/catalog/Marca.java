package pe.fyj.fyj_erp_api.models.catalog;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "marca")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Marca {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_marca")
private Long id;

@Column(name = "nombre_marca", nullable = false, length = 150)
private String nombre;

@Column(name = "estado", nullable = false)
private Boolean estado = true;
}