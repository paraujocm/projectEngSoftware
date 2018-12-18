package engsoftware.project.controllers;

import engsoftware.project.models.Consulta;
import engsoftware.project.models.Paciente;
import engsoftware.project.services.ConsultaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

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

    /*
    // cria uma consulta e add ao paciente
    @RequestMapping (value = "/{nr_utente_saude}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Consulta createConsulta(@PathVariable ("nr_utente_saude") String nr_utente_saude, @RequestBody Consulta consulta){
        Paciente paciente = consultaService.findByNr_utente_saude(nr_utente_saude).get();
        paciente.addConsutaToPaciente(consulta);
        consultaService.save(consulta);
        return consulta;
    }*/

    @PostMapping(value = "/{nr_utente_saude}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> saveConsulta(@RequestBody Consulta consulta, @PathVariable("nr_utente_saude") String nr_utente_saude){
        logger.info(consulta.toString()+" "+nr_utente_saude);
        Optional<Consulta> consultaOptional= consultaService.saveConsulta(consulta, nr_utente_saude);
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
