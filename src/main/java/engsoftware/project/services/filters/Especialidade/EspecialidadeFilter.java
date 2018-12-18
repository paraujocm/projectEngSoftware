package engsoftware.project.services.filters.Especialidade;

import engsoftware.project.models.Especialidade;

import java.util.Set;

public interface EspecialidadeFilter {
    Set<Especialidade> filter(Set<Especialidade> entities);
}
