package engsoftware.project.controllers;

import engsoftware.project.models.Medico;
import engsoftware.project.services.MedicoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.util.Optional;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    private MedicoService medicoService;

    private Logger logger= LoggerFactory.getLogger(ConsultaController.class);

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Medico> getAllMedico(){
        return medicoService.findAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody Medico getById(@PathVariable("id") Long id){
        return medicoService.findById(id).get();
    }

    @RequestMapping(value="/{name}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody Medico getByName(@PathVariable("name") String name){
        return medicoService.findByName(name).get();
    }

//    @PostMapping(value = "/{nameMedico}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Medico> saveMedico(@RequestBody Medico medico, @PathVariable("nameMedico") String nameMedico){
//        logger.info(medico.toString()+" "+nameMedico);
//        Optional<Medico> medicoOptional= medicoService.saveMedico(nameMedico);
//        if(medicoOptional.isPresent()){
//            return ResponseEntity.ok(medicoOptional.get());
//        }
//        return ResponseEntity.notFound().build();
//    }

    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> savePaciente(@RequestBody Medico medico){
        return medicoService.saveMedico(medico);
    }


    @PostMapping(value = "/remove/{nameMedico}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> removeMedico(@PathVariable("nameMedico") String nameMedico){
        //logger.info(especialidade.toString()+" "+nameMedico);
        Optional<Medico> medicoOptional= medicoService.removeMedico(nameMedico);
        if(medicoOptional.isPresent()){
            return ResponseEntity.ok(medicoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
//
//    @RequestMapping (value = "/{nameMedico}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody Medico removeMedico(@PathVariable ("nameMedico") String nameMedico, @RequestBody Medico medico){
//        Medico medico1= medicoService.findByName(nameMedico).get() ;
//        medico1.removeMedico(medico);
//        return medico;
//    }


}
