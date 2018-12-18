package engsoftware.project.models;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Consulta extends BaseModel {

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @ToString.Exclude
    private Medico medico;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @ToString.Exclude
    private Paciente paciente;

    private WorkTime horario;
    private Especialidade tipo;

    public Consulta ( Paciente nr_utente, WorkTime horario, Especialidade tipo, Medico medico) {
        this.paciente=nr_utente;
        this.horario=horario;
        this.tipo=tipo;
        this.medico=medico;
    }
}
