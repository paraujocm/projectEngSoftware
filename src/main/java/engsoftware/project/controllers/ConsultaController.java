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

    @PostMapping(value = "/{nr_utente_saude}/{nameMedico}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> saveConsulta(@RequestBody Consulta consulta, @PathVariable("nr_utente_saude") String nr_utente_saude, @PathVariable("nameMedico") String nameMedico){
        logger.info(consulta.toString()+" "+nr_utente_saude);
        Optional<Consulta> consultaOptional= consultaService.saveConsulta(consulta, nr_utente_saude, nameMedico);
        if(consultaOptional.isPresent()){
            return ResponseEntity.ok(consultaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    //remove consuta from paciente
    @RequestMapping (value = "/{nr_utente_saude}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Consulta removeConsulta(@PathVariable ("nr_utente_saude") String nr_utente_saude, @RequestBody Consulta consulta){
        Paciente paciente = consultaService.findByNr_utente_saude(nr_utente_saude).get();
        paciente.removeConsultaFromPaciente(consulta);
        consultaService.save(consulta);
        return consulta;
    }
}
