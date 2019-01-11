package engsoftware.project.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import engsoftware.project.ProjectApplicationTests;
import engsoftware.project.models.Especialidade;
import engsoftware.project.models.Medico;
import engsoftware.project.services.MedicoServiceI;
import engsoftware.project.services.filters.Medico.FilterObjectMedico;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplicationTests.class)
@AutoConfigureMockMvc
@Transactional
public class MedicoControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MedicoServiceI medicoServiceI;

    @Autowired
    ObjectMapper mapper;

    @Before
    public void setUp() {

        Especialidade dentista = new Especialidade("Dentista", 30);

        Medico medico = new Medico("medico1","medico@gmail.com","918765432", dentista);
        medicoServiceI.save(medico);
    }

    @Test
    public void getAllCourse() throws Exception {
        Iterable<Medico> medicos = medicoServiceI.getFilteredMedico(new FilterObjectMedico());
        mvc.perform(get("/medico").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(medicos)))
                .andReturn();
    }

    @Test
    public void getById() throws Exception {
        Medico medico = medicoServiceI.findById(1l).get();
        mvc.perform(get("/medico/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(medico.getNome()))
                .andReturn();

        mvc.perform(get("/medico/error").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getByName() throws Exception {
        Medico medico = medicoServiceI.findByName("medico1").get();
        mvc.perform(get("/medico/name/medico1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(medico.getNome()))
                .andReturn();

        mvc.perform(get("/medico/name/error").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());

    }
}
