package engsoftware.project.services.filters.Medico;

import engsoftware.project.models.Especialidade;
import engsoftware.project.models.WorkTime;
import lombok.Data;

@Data
public class FilterObjectMedico {
    private String nome;
    private Especialidade especialidade;
    private WorkTime dia;
    private WorkTime horaInicio;
    private WorkTime horaFim;
}
