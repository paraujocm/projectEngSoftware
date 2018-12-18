package engsoftware.project.services;

import engsoftware.project.models.Paciente;
import engsoftware.project.services.filters.AndFilter;
import engsoftware.project.services.filters.FilterI;
import engsoftware.project.services.filters.Paciente.*;

import java.util.Set;

public class PacienteFilterService {
    public Set<Paciente> filterPacientes(Set<Paciente> pacientes, FilterObjectPaciente filterObjectPaciente) {
        System.out.println(filterObjectPaciente);
        FilterI<Paciente> pacienteNomeFilter = new PacienteNomeFilter(filterObjectPaciente.getNome());
        FilterI<Paciente> pacienteNrUtenteFilter = new PacienteNrUtenteFilter(filterObjectPaciente.getNr_utente_saude());
        FilterI<Paciente> nomeAndNrUtenteFilter = new AndFilter<>(pacienteNomeFilter, pacienteNrUtenteFilter);
        FilterI<Paciente> pacienteProblematicoFilter = new PacienteProblematicoFilter(filterObjectPaciente.isProblematico());
        FilterI<Paciente> nomeAndNrUtenteAndProblematicoFilter = new AndFilter<>(nomeAndNrUtenteFilter, pacienteProblematicoFilter);
        return nomeAndNrUtenteAndProblematicoFilter.filter(pacientes);
    }
}


