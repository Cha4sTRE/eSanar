package cj.esanar.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"paciente","consultas"})
@Entity
@Table(name = "historia_clinica")
public class HistoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @OneToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private PacienteEntity paciente;

    @OneToMany(mappedBy = "historiaClinica",
            cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)// NO RECOMENDADO: carga inmediatamente las consultas y las historias
    @Builder.Default
    private Set<ConsultaEntity> consultas= new HashSet<>();


    public void agregarConsultas(ConsultaEntity consulta) {
        this.consultas.add(consulta);
        consulta.setHistoriaClinica(this);
    }


}
