package engsoftware.project;

import engsoftware.project.models.*;
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
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        Especialidade dentista= new Especialidade("Dentista", 30);
        Especialidade geral= new Especialidade("Clinica Geral", 30);

        Medico medico1= new Medico("Joao Coelho", "joao@gmail.com", "912345678", dentista);
        Medico medico2= new Medico("Pedro Mota", "pedro@gmail.com", "918765432", geral);
        Medico medico3= new Medico("Zezinho", "ze@gmail.com", "987654321", geral);

        Set<WorkTime> workTimes1= new HashSet<>();
        WorkTime workTime1= new WorkTime();
        workTime1.setDay(DayOfWeek.MONDAY);
        workTime1.setStart(LocalTime.of(9,30));
        workTime1.setEnd(LocalTime.of(16, 0));
        workTimes1.add(workTime1);
        WorkTime workTime2= new WorkTime();
        workTime1.setDay(DayOfWeek.WEDNESDAY);
        workTime1.setStart(LocalTime.of(13, 20));
        workTime1.setEnd(LocalTime.of(22,0));
        workTimes1.add(workTime2);
        medico1.setWorkTimes(workTimes1);

        Set<WorkTime> workTimes2= new HashSet<>();
        WorkTime workTime3= new WorkTime();
        workTime3.setDay(DayOfWeek.TUESDAY);
        workTime3.setStart(LocalTime.of(11,30));
        workTime3.setEnd(LocalTime.of(17, 15));
        workTimes2.add(workTime3);
        WorkTime workTime4= new WorkTime();
        workTime4.setDay(DayOfWeek.THURSDAY);
        workTime4.setStart(LocalTime.of(6, 0));
        workTime4.setEnd(LocalTime.of(14,0));
        workTimes2.add(workTime4);
        medico2.setWorkTimes(workTimes2);

        Set<WorkTime> workTimes3= new HashSet<>();
        WorkTime workTime5= new WorkTime();
        workTime5.setDay(DayOfWeek.FRIDAY);
        workTime5.setStart(LocalTime.of(12,30));
        workTime5.setEnd(LocalTime.of(18, 0));
        workTimes3.add(workTime5);
        WorkTime workTime6= new WorkTime();
        workTime6.setDay(DayOfWeek.SATURDAY);
        workTime6.setStart(LocalTime.of(7, 20));
        workTime6.setEnd(LocalTime.of(12,30));
        workTimes3.add(workTime6);
        medico3.setWorkTimes(workTimes3);

       Paciente paciente1= new Paciente("Ana", 21, "234567890", false, "123456789");
       Paciente paciente2= new Paciente("Luis", 71, "224365879", false, "180333111");
       Paciente paciente3= new Paciente("Rafael", 35, "214365879", false, "189333111");
       Paciente paciente4= new Paciente("Rui", 50, "264365879", false, "199333111");

       WorkTime consultaWT= new WorkTime();
       consultaWT.setDay(DayOfWeek.MONDAY);
       consultaWT.setStart(LocalTime.of(10,0));
       Consulta consulta1= new Consulta();
       consulta1.setHorario(LocalDateTime.of(2019,1,20,10,0));

       medico1.addConsutaToMedico(consulta1);
       paciente1.addConsutaToPaciente(consulta1);
    }

    /*private Set<Medico> createMedicosFromFile() throws IOException {
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
    }*/

    private Medico getByName(String name,Set<Medico>medicos){
        for(Medico medico:medicos){
            if(medico.getNome().equalsIgnoreCase(name)){
                return medico;
            }
        }
        return null;
    }
}
