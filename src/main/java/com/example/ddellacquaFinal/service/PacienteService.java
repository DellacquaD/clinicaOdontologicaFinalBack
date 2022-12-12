package com.example.ddellacquaFinal.service;
import com.example.ddellacquaFinal.entity.Paciente;
import com.example.ddellacquaFinal.exception.ResourceNotFoundException;
import com.example.ddellacquaFinal.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class PacienteService {

    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);

    PacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository){
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardarPaciente (Paciente paciente){
        LOGGER.info("Se solicita el guardado de un Paciente");
        return pacienteRepository.save(paciente);
    }

    public void actualizarPaciente (Paciente paciente){
        LOGGER.info("Se solicita actualizar el paciente con ID" + paciente.getId());
        pacienteRepository.save(paciente);
    }
    public void eliminarPaciente (Long id) throws ResourceNotFoundException {
        LOGGER.warn("Se solicita eliminar el paciente con ID" + id);
        pacienteRepository.deleteById(id);
    }
    public Optional<Paciente> buscarPaciente (Long id){
        LOGGER.info("Se solicita actualizar el paciente con ID" + id);
        return pacienteRepository.findById(id);
    }
    public List<Paciente> listarPacientes(){
        LOGGER.info("Se solicita el listado de todos los pacientes" );
        return pacienteRepository.findAll();
    }


}
