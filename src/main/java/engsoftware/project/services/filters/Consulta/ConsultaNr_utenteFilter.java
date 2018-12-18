package engsoftware.project.services.filters.Consulta;

import engsoftware.project.models.Consulta;
import engsoftware.project.models.Paciente;

import java.util.Set;
import java.util.stream.Collectors;

public class ConsultaNr_utenteFilter implements ConsultaFilter {

    private Paciente nr_utenteToFilter;

    public ConsultaNr_utenteFilter(Paciente nr_utenteToFilter) {
        this.nr_utenteToFilter = nr_utenteToFilter;
    }

    @Override
    public Set<Consulta> filter(Set<Consulta> consultas) {
        if(nr_utenteToFilter==null)return consultas;

        return consultas.stream()
                .filter(consulta -> consulta.getPaciente()==this.nr_utenteToFilter)
                .collect(Collectors.toSet());
    }
}
