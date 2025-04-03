package cj.esanar.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"historiaClinica"})
@Entity
@Table(name = "consulta")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ///
    /// Atributo que relaciona {@link HistoriaEntity} con muchas consultas, esta relacion es de **Muchos a Uno**
    /// ósea que **muchas** consultas pertenecen a **una** historia
    ///
    @ManyToOne
    @JoinColumn(name = "id_historia", nullable = false)
    private HistoriaEntity historiaClinica;

    @ManyToOne
    @JoinColumn(name = "id_enfermera")
    private UserEntity enfermera;

    @Column(name = "diagnostico_principal")
    private String diagnosticoPrincipal;

    @Column(name = "motivo_consulta")
    private String motivoConsulta;

    @Column(name = "largo")
    private Integer largo;

    @Column(name = "ancho")
    private Integer ancho;

    @Column(name = "profundidad")
    private String profundidad;

    @Column(name = "forma")
    private String forma;

    @Column(name = "olor")
    private String olor;

    @Column(name = "bordes_herida")
    private String bordesHerida;

    @Column(name = "infeccion")
    private String infeccion;

    @Column(name = "exudado_tipo")
    private String exudadoTipo;

    @Column(name = "exudado_nivel")
    private String exudadoNivel;

    @Column(name = "fecha_hora_atencion")
    private LocalDateTime fechaHoraAtencion;

    @Column(name = "hora_final")
    private LocalTime horaFinal;

}
