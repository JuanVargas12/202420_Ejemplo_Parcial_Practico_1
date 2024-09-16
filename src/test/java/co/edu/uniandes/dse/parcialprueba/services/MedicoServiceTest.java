package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;


import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoService.class)

class MedicoServiceTest {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<MedicoEntity> medicoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        medicoRepository.deleteAll();
    }

    private void insertData() {
        // Crear y persistir médicos
        for (int i = 0; i < 3; i++) {
            MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
            medicoRepository.save(medicoEntity);
            medicoList.add(medicoEntity);
        }
    }

    @Test
    public void testCreateMedico()throws EntityNotFoundException, IllegalOperationException {
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        // Configura el registroMedico con un prefijo específico
        newEntity.setRegistroMedico("RM" + newEntity.getRegistroMedico());

        MedicoEntity result = medicoService.createMedico(newEntity);
        assertNotNull(result);

        MedicoEntity entity = entityManager.find(MedicoEntity.class,result.getId());

        assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getNombre(), entity.getNombre());
		assertEquals(newEntity.getApellido(), entity.getApellido());
        assertEquals(newEntity.getRegistroMedico(), entity.getRegistroMedico());
        
    }
    
}