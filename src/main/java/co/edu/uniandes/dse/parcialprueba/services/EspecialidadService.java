package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import jakarta.transaction.Transactional;

public class EspecialidadService {

    @Autowired
    EspecialidadRepository especialidadRepository;
    
    @Transactional
    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidadEntity )throws EntityNotFoundException, IllegalOperationException{
        /**
         * Primero verifica que se cumplan las reglas de negocio,
         * y después llama a la persistencia
         */
         validateEspecialidad(especialidadEntity); 
         /**
         * Aqui se hace el llamado a la persistencia, y la base de datos 
         * crea un nuevo ID para el elemento, en este caso, el concursoEntity
         */
         return especialidadRepository.save(especialidadEntity);
    }

    private void validateEspecialidad( EspecialidadEntity especialidad ) throws IllegalOperationException
    {
        // regla de negocio: nombre!=null
        if ( especialidad.getNombre() == null ) throw new IllegalOperationException( "Nombre is not valid" );

        // regla de negocio: apellido != null y  Valide que la descripción tenga como mínimo 10 caracteres.
        if ( especialidad.getDescripcion() == null ) throw new IllegalOperationException( "descripction is not valid" );
        if ( especialidad.getDescripcion().length()>=10) throw new IllegalOperationException( "descripction is not valid" );
    }
    


}
