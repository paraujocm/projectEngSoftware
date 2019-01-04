package engsoftware.project.services.filters.Medico;

import engsoftware.project.models.Medico;
import engsoftware.project.services.filters.FilterI;

import java.util.Set;

public interface MedicoFilter extends FilterI<Medico> {
    Set<Medico> filter(Set<Medico> entities);
}
