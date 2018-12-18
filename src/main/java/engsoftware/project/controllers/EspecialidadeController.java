package engsoftware.project.controllers;

import engsoftware.project.models.Consulta;
import engsoftware.project.models.Especialidade;
import engsoftware.project.models.Medico;
import engsoftware.project.models.Paciente;
import engsoftware.project.repositories.EspecialidadeRepoI;
import engsoftware.project.services.EspecialidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/especialidade")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    private Logger logger= LoggerFactory.getLogger(ConsultaController.class);

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Especialidade> getAllEspecialidade(){ return especialidadeService.findAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody Especialidade getById(@PathVariable("id") Long id){
        return especialidadeService.findById(id).get();
    }

    @RequestMapping(value="/{name}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody Especialidade getById(@PathVariable("name") String name){
        return especialidadeService.findByName(name).get();
    }


    @PostMapping(value = "/{nameMedico}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Especialidade> saveEspecialidade(@RequestBody Especialidade especialidade, @PathVariable("nameMedico") String nameMedico){
        logger.info(especialidade.toString()+" "+nameMedico);
        Optional<Especialidade> especialidadeOptional= especialidadeService.saveEspecialidade(especialidade, nameMedico);
        if(especialidadeOptional.isPresent()){
            return ResponseEntity.ok(especialidadeOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    //remove consuta from paciente
    @RequestMapping (value = "/{nameMedico}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Especialidade removeEspecialidade(@PathVariable ("nameMedico") String nameMedico, @RequestBody Especialidade especialidade){
        Medico medico = especialidadeService.findByMedico(nameMedico).get() ;
        medico.removeEspecialidadeFromMedico(especialidade);
        especialidadeService.save(especialidade);
        return especialidade;
    }
}
