package cj.esanar.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pacientes")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "tipo_documento", nullable = false, length = 50)
    private String tipoDocumento;

    @Column(name = "identificacion")
    private Long identificacion;

    @Column(name = "telefono", nullable = false, length = 20)
    private Long telefono;

    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @Column(name = "barrio", nullable = false, length = 50)
    private String barrio;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "sexo", nullable = false, length = 10)
    private String sexo;

    @Column(name = "tipo_sangre", nullable = false, length = 5)
    private String tipoSangre;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "ocupacion", nullable = false, length = 50)
    private String ocupacion;

    @Column(name = "entidad", nullable = false, length = 50)
    private String entidad;

    @Column(name = "estado_civil", nullable = false, length = 20)
    private String estadoCivil;

    /// Atributo que relaciona la entidad **Paciente** con la entidad **Historia**.
    /// En la base de datos esta relacion es de **Uno a Uno**, ósea que los pacientes tiene una historia
    /// y las historias tienen un paciente
    /// - mappedBy: mapea este **paciente** en el atributo 'paciente' de {@link HistoriaEntity},
    /// esto genera la llave foránea en la base de datos


    @OneToOne(mappedBy = "paciente", orphanRemoval = true,cascade = CascadeType.PERSIST)
    private HistoriaEntity historiaEntity;

}
