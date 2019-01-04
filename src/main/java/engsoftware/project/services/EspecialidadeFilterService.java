package engsoftware.project.services;

import engsoftware.project.models.Especialidade;
import engsoftware.project.services.filters.Especialidade.EspecialidadeFilter;
import engsoftware.project.services.filters.Especialidade.EspecialidadeNomeFilter;
import engsoftware.project.services.filters.Especialidade.FilterObjectEspecialidade;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EspecialidadeFilterService {
    public Set<Especialidade> filterEspecialidade(Set<Especialidade> especialidades, FilterObjectEspecialidade filterObjectEspecialidade) {
        System.out.println(filterObjectEspecialidade);
        EspecialidadeFilter EspecialidadeNomeFilter = new EspecialidadeNomeFilter(filterObjectEspecialidade.getNome());
        return EspecialidadeNomeFilter.filter(especialidades);
    }
}
