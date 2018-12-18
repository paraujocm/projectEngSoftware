package engsoftware.project.controllers;

import engsoftware.project.models.Paciente;
import engsoftware.project.services.PacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private PacienteService pacienteService;

    private Logger logger= LoggerFactory.getLogger(ConsultaController.class);

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Paciente> getAllPaciente(){
        return pacienteService.findAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody
    Paciente getById(@PathVariable("id") Long id){
        return pacienteService.findById(id).get();
    }

    @RequestMapping(value="/{nr_utente_saude}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody
    Paciente getById(@PathVariable("nr_utente_saude") String nr_utente_saude){
        return pacienteService.findByNr_utente_saude(nr_utente_saude).get();
    }

    @PostMapping(value = "/{nr_utente_saude}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> savePaciente(@RequestBody Paciente paciente, @PathVariable("nr_utente_saude") String nr_utente_saude){
        logger.info(paciente.toString()+" "+nr_utente_saude);
        Optional<Paciente> pacienteOptional= pacienteService.savePaciente(nr_utente_saude);
        if(pacienteOptional.isPresent()){
            return ResponseEntity.ok(pacienteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping (value = "/{nr_utente_saude}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Paciente removePaciente(@PathVariable ("nr_utente_saude") String nr_utente_saude, @RequestBody Paciente paciente){
        Paciente paciente1= pacienteService.findByNr_utente_saude(nr_utente_saude).get() ;
        paciente1.removePaciente(paciente);
        return paciente;
    }
}
