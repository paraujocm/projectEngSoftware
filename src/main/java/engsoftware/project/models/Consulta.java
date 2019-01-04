package engsoftware.project.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

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

    private LocalDateTime horario;
    private LocalDateTime fimExpectavel;
    private Especialidade tipo;

    public Consulta ( Paciente nrUtente, LocalDateTime horario, Especialidade tipo, Medico medico) {
        this.paciente=nrUtente;
        this.horario=horario;
        this.fimExpectavel=horario.plusMinutes(30);
        this.tipo=tipo;
        this.medico=medico;
    }
}
