package cj.esanar.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "historia_clinica")
public class HistoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    public void agregarConsultas(ConsultaEntity consulta) {
        this.consultas.add(consulta);
        consulta.setHistoriaClinica(this);
    }

    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ConsultaEntity> consultas= new HashSet<>();

    @OneToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private PacienteEntity paciente;

}
