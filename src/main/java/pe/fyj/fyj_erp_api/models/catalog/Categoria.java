package pe.fyj.fyj_erp_api.models.catalog;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Categoria {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_categoria")
private Long id;

@Column(name = "nombre_categoria", nullable = false, length = 150)
private String nombre;

@Column(name = "estado", nullable = false)
private Boolean estado = true;
}