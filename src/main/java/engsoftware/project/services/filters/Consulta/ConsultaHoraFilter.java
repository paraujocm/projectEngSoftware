package engsoftware.project.services.filters.Consulta;

import engsoftware.project.models.Consulta;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsultaHoraFilter implements ConsultaFilter {

    private LocalTime horaToFilter;

    public ConsultaHoraFilter(LocalTime horaToFilter) {
        this.horaToFilter = horaToFilter;
    }

    @Override
    public Set<Consulta> filter(Set<Consulta> consultas) {
        if(horaToFilter==null)return consultas;

        return consultas.stream()
                .filter(consulta -> consulta.getHorario().getStart()==this.horaToFilter)
                .collect(Collectors.toSet());
    }
}
