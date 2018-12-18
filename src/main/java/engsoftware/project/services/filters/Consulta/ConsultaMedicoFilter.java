package engsoftware.project.services.filters.Consulta;

import engsoftware.project.models.Consulta;
import engsoftware.project.models.Medico;

import java.util.Set;
import java.util.stream.Collectors;

public class ConsultaMedicoFilter implements  ConsultaFilter{

    private Medico medicoToFilter;

    public ConsultaMedicoFilter(Medico medicoToFilter) {
        this.medicoToFilter = medicoToFilter;
    }

    @Override
    public Set<Consulta> filter(Set<Consulta> consultas) {
        if(medicoToFilter==null)return consultas;

        return consultas.stream()
                .filter(consulta -> consulta.getMedico()==this.medicoToFilter)
                .collect(Collectors.toSet());
    }
}
