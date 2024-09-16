package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import java.util.*;

public class MedicoEspecialidadService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    EspecialidadRepository especialidadRepository;

    private String especialidaNoEncontrada = "Especialidad no encontrado";
    private String medicoNoEncontrada = "medico no encontrado";

    @Transactional
    public EspecialidadEntity addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException {
        Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(especialidadId);
        if (especialidadEntity.isEmpty())
            throw new EntityNotFoundException(especialidaNoEncontrada);

        Optional<MedicoEntity> medicoEntity = medicoRepository.findById(medicoId);
        if (medicoEntity.isEmpty())
            throw new EntityNotFoundException(medicoNoEncontrada);

        // Asociar la especialidad al médico
        medicoEntity.get().getEspecialidades().add(especialidadEntity.get());
        return especialidadEntity.get();
    }

}
