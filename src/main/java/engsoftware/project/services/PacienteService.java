package engsoftware.project.services;


import engsoftware.project.models.Paciente;
import engsoftware.project.repositories.PacienteRepoI;
import engsoftware.project.services.filters.Paciente.FilterObjectPaciente;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService implements PacienteServiceI {

    private PacienteRepoI pacienteRepoI;

    private PacienteFilterService pacienteFilterService;

    public PacienteService(PacienteRepoI pacienteRepoI, PacienteFilterService pacienteFilterService) {
        this.pacienteRepoI = pacienteRepoI;
        this.pacienteFilterService = pacienteFilterService;
    }

    @Override
    public Set<Paciente> getSetPaciente(){
        Set<Paciente> pacientes=new HashSet<>();
        for(Paciente paciente:this.pacienteRepoI.findAll()){
            pacientes.add(paciente);
        }
        return pacientes;
    }

    @Override
    public Set<Paciente> getFilteredPaciente(FilterObjectPaciente filterObjectPaciente){
        return pacienteFilterService.filterPacientes(findAll(),filterObjectPaciente);
    }

    @Override
    public Set<Paciente> findAll(){
        Set<Paciente> pacientes=new HashSet<>();
        for(Paciente paciente:this.pacienteRepoI.findAll()){
            pacientes.add(paciente);
        }
        return Collections.unmodifiableSet(pacientes);
    }

    @Override
    public Optional<Paciente> findByNrUtenteSaude(String nrUtenteSaude) {
        return pacienteRepoI.findByNrUtenteSaude(nrUtenteSaude);
    }

    @Override
    public Optional<Paciente> findById(Long id) {

        return pacienteRepoI.findById(id);
        /*if(courseOptional.isPresent()){
            return Optional.of(courseOptional.get().getDTO());
        }
        return Optional.empty();*/
    }

    @Override
    public Paciente save(Paciente paciente){
        return this.pacienteRepoI.save(paciente);
    }


    @Override
    public Optional<Paciente> savePaciente(String nrUtenteSaude) {
        Optional<Paciente> pacienteOptional=this.pacienteRepoI.findByNrUtenteSaude(nrUtenteSaude);
        if(pacienteOptional.isPresent()){
            Paciente paciente=pacienteOptional.get();

            paciente.addPaciente(paciente);
            pacienteRepoI.save(paciente);
            return pacienteRepoI.findByNrUtenteSaude(nrUtenteSaude);
        }
        return Optional.empty();
    }
}
