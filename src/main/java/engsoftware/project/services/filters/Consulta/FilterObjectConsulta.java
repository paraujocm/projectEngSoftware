package engsoftware.project.services.filters.Consulta;

import engsoftware.project.models.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class FilterObjectConsulta {
    private Paciente nr_utente;
    private Especialidade especialidade;
    private DayOfWeek dia;
    private LocalTime hora;
    private Medico medico;
}
