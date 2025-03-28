package cj.esanar.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "consulta")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_historia", nullable = false)
    private HistoriaEntity historiaClinica;

    @ManyToOne
    @JoinColumn(name = "id_enfermera")
    private UserEntity enfermera;

    @Column(name = "diagnostico_principal", length = 50)
    private String diagnosticoPrincipal;

    @Column(name = "motivo_consulta", length = 255)
    private String motivoConsulta;

    @Column(name = "largo")
    private Integer largo;

    @Column(name = "ancho")
    private Integer ancho;

    @Column(name = "profundidad", length = 50)
    private String profundidad;

    @Column(name = "forma", length = 50)
    private String forma;

    @Column(name = "olor", length = 3)
    private String olor;

    @Column(name = "bordes_herida", length = 50)
    private String bordesHerida;

    @Column(name = "infeccion", length = 50)
    private String infeccion;

    @Column(name = "exudado_tipo", length = 50)
    private String exudadoTipo;

    @Column(name = "exudado_nivel", length = 50)
    private String exudadoNivel;

    @Column(name = "fecha_hora_atencion")
    private LocalDateTime fechaHoraAtencion;

    @Column(name = "hora_final")
    private LocalTime horaFinal;

}
