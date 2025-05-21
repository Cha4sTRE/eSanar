package cj.esanar.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

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

    /// {@link PacienteEntity} relacionado con esta historia
    @OneToOne(cascade =CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "id_paciente", nullable = false)
    private PacienteEntity paciente;

    ///
    /// Atributo que relaciona {@link ConsultaEntity} con una historia, este relacion es de tipo
    /// **Uno a Muchos**, ósea que **muchas** consultas pertenecen a **una** historia
    ///
    @OneToMany(mappedBy = "historiaClinica", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ConsultaEntity> consultas= new HashSet<>();


    /// Metodo para agregar consultas dentro de una historia
    /// @param consulta consulta a agregar en la historia
    ///
    public void agregarConsultas(ConsultaEntity consulta) {
        this.consultas.add(consulta);
        consulta.setHistoriaClinica(this);
    }


}
