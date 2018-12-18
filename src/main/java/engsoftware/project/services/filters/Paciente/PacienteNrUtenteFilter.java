package engsoftware.project.services.filters.Paciente;

import engsoftware.project.models.Paciente;

import java.util.Set;
import java.util.stream.Collectors;

public class PacienteNrUtenteFilter implements PacienteFilter {

    private String nrUtenteToFilter;

    public PacienteNrUtenteFilter(String nrUtenteToFilter) {
        this.nrUtenteToFilter = nrUtenteToFilter;
    }

    @Override
    public Set<Paciente> filter(Set<Paciente> pacientes) {
        if(nrUtenteToFilter==null)return pacientes;

        return pacientes.stream()
                .filter(paciente -> paciente.getNr_utente_saude()==this.nrUtenteToFilter)
                .collect(Collectors.toSet());
    }
}
