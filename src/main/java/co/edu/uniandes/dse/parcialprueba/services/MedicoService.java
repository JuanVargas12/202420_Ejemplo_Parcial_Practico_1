package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;
    
    @Transactional
    public MedicoEntity createMedico(MedicoEntity medicoEntity )throws EntityNotFoundException, IllegalOperationException{
        /**
         * Primero verifica que se cumplan las reglas de negocio,
         * y después llama a la persistencia
         */
         validateMedico(medicoEntity); 
         /**
         * Aqui se hace el llamado a la persistencia, y la base de datos 
         * crea un nuevo ID para el elemento, en este caso, el concursoEntity
         */
         return medicoRepository.save(medicoEntity);
    }

    private void validateMedico( MedicoEntity medico ) throws IllegalOperationException
    {
        // regla de negocio: nombre!=null
        if ( medico.getNombre() == null ) throw new IllegalOperationException( "Miembros is not valid" );

        // regla de negocio: apellido != null
        if ( medico.getApellido() == null ) throw new IllegalOperationException( "Eventos is not valid" );

        // regla de negocio: registro != null y tiene que empezar con RM
        if ( medico.getRegistroMedico() == null ) throw new IllegalOperationException( "Concursos is not valid" );
        if (!medico.getRegistroMedico().startsWith("RM")) throw new IllegalArgumentException("El registro médico debe comenzar con 'RM'.");
        
    }
    


}
