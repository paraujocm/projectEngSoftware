package engsoftware.project.controllers;

import engsoftware.project.models.Consulta;
import engsoftware.project.models.Paciente;
import engsoftware.project.services.ConsultaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    private ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    private Logger logger= LoggerFactory.getLogger(ConsultaController.class);

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Consulta> getAllConsulta(){
        return consultaService.findAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Consulta getById(@PathVariable("id") Long id){
        Optional<Consulta> courseDTOOptional= consultaService.findById(id);
        return courseDTOOptional.orElse(null);
    }

    @PostMapping(value = "/{nrUtenteSaude}/{nameMedico}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> saveConsulta(@RequestBody Consulta consulta, @PathVariable("nrUtenteSaude") String nrUtenteSaude, @PathVariable("nameMedico") String nameMedico){
        logger.info(consulta.toString()+" "+nrUtenteSaude);
        Optional<Consulta> consultaOptional= consultaService.saveConsulta(consulta, nrUtenteSaude, nameMedico);
        if(consultaOptional.isPresent()){
            return ResponseEntity.ok(consultaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    //remove consuta from paciente
    @RequestMapping (value = "/{nrUtenteSaude}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Consulta removeConsulta(@PathVariable ("nrUtenteSaude") String nrUtenteSaude, @RequestBody Consulta consulta){
        Paciente paciente = consultaService.findByNrUtenteSaude(nrUtenteSaude).get();
        paciente.removeConsultaFromPaciente(consulta);
        consultaService.save(consulta);
        return consulta;
    }
}
