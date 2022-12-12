package com.example.ddellacquaFinal.service;
import com.example.ddellacquaFinal.dto.TurnoDTO;
import com.example.ddellacquaFinal.entity.Odontologo;
import com.example.ddellacquaFinal.entity.Paciente;
import com.example.ddellacquaFinal.entity.Turno;
import com.example.ddellacquaFinal.exception.ResourceNotFoundException;
import com.example.ddellacquaFinal.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private static final Logger LOGGER = Logger.getLogger(TurnoService.class);

    private TurnoRepository turnoRepository;
    @Autowired
    public TurnoService(TurnoRepository turnoRepository){
        this.turnoRepository = turnoRepository;
    }
    public TurnoDTO guardarTurno (TurnoDTO turno){
        LOGGER.info("Se solicita guardar un nuevo turno");

        Turno turnoAGuardar=turnoDTOaTurno(turno);
        Turno turnoGuardado=turnoRepository.save(turnoAGuardar);
        return turnoATurnoDTO(turnoGuardado);
    }
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        LOGGER.warn("Se solicita eliminar un turno");
        turnoRepository.deleteById(id);
    }
    public void actualizarTurno(TurnoDTO turno){
        LOGGER.info("Se solicita actualizar el paciente con ID");
        Turno turnoAActualizar=turnoDTOaTurno(turno);
        turnoRepository.save(turnoAActualizar);
    }
    public Optional<TurnoDTO> buscarTurno(Long id){
        LOGGER.info("Se solicita la informacion del turno con ID" + id);
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            LOGGER.info("Se devuelve la informacion del turno con ID" + id);
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }
        else{
            LOGGER.info("No se encontro el turno con" + id);
            return Optional.empty();
        }
    }
    public List<TurnoDTO> buscarTodosTurno(){
        LOGGER.info("Se solicita la informacion de todos los turnos");
        List<Turno>turnosEncontrados=turnoRepository.findAll();
        List<TurnoDTO> respuesta= new ArrayList<>();
        for (Turno t:turnosEncontrados) {
            respuesta.add(turnoATurnoDTO(t));
        }
        return respuesta;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        //convertir ese turno en un turno DTO
        TurnoDTO respuesta=new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setFecha(turno.getFecha());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        return respuesta;
    }
    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        Turno turno= new Turno();
        Paciente paciente= new Paciente();
        Odontologo odontologo= new Odontologo();
        //cargar los elementos
        paciente.setId(turnoDTO.getPacienteId());
        odontologo.setId(turnoDTO.getOdontologoId());
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        //asociar cada elemento
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        //salida
        return turno;
    }

}
