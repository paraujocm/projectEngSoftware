package engsoftware.project;

import engsoftware.project.models.Especialidade;
import engsoftware.project.models.Medico;
import engsoftware.project.models.Paciente;
import engsoftware.project.repositories.ConsultaRepoI;
import engsoftware.project.repositories.MedicoRepoI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;


@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger= LoggerFactory.getLogger(Bootstrap.class);

    private MedicoRepoI medicoRepoI;

    private ConsultaRepoI consultaRepoI;

    public Bootstrap(MedicoRepoI medicoRepoI, ConsultaRepoI consultaRepoI) {
        this.medicoRepoI = medicoRepoI;
        this.consultaRepoI = consultaRepoI;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Set<Medico> medicos = new HashSet<>();
        try {
            medicos = createMedicosFromFile();
            Paciente paciente1 = new Paciente("Pedro", 21, "242411436", false, "213456789");
            Paciente paciente2 = new Paciente("Joao", 21, "242411435", false, "213856789");
            int i=0;
            for(Medico m:medicos){
                if(i==0){

                }else if(i==1){

                }
                i++;
            }
        }catch (Exception e){

        }
    }


    private Set<Medico> createMedicosFromFile() throws IOException {
        Set<Medico> medicos=new HashSet<>();
        String line;

//      InputStream is = this.getClass().getResourceAsStream("/medicos.txt");
        InputStream is = new ClassPathResource("medicos.txt").getInputStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            while((line=br.readLine())!=null){
                String attributes[]=line.split(",");
                Especialidade especialidade = new Especialidade(attributes[3], Float.parseFloat(attributes[4]));
                Medico medico=new Medico(attributes[0],attributes[1], attributes[2], especialidade); // como se faz nome medico, especialidade, quero meter o wroktime tambem
                medicos.add(medico);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    private Medico getByName(String name,Set<Medico>medicos){
        for(Medico medico:medicos){
            if(medico.getNome().equalsIgnoreCase(name)){
                return medico;
            }
        }
        return null;
    }
}
