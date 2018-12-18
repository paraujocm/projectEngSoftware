package engsoftware.project.services;

import engsoftware.project.models.Consulta;
import engsoftware.project.models.Medico;
import engsoftware.project.models.Paciente;
import engsoftware.project.models.WorkTime;
import engsoftware.project.services.filters.Consulta.FilterObjectConsulta;

import java.util.Optional;
import java.util.Set;

public interface ConsultaServiceI {

        Set<Consulta> getSetConsulta();

        Set<Consulta> getFilteredConsulta(FilterObjectConsulta filterObjectConsulta);

        Set<Consulta> findAll();

        Optional<Consulta> findById(Long id);

        Optional<Medico> findByName(String nameMedico);

        Optional<Paciente> findByNr_utente_saude(String nr_utente_saude);

        Consulta save(Consulta consulta);

        Optional<Consulta> saveConsulta(Medico medico, Consulta consulta, String nr_utente_saude);

}
