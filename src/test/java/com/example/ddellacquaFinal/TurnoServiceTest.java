package com.example.ddellacquaFinal;
import com.example.ddellacquaFinal.dto.TurnoDTO;
import com.example.ddellacquaFinal.entity.Domicilio;
import com.example.ddellacquaFinal.entity.Odontologo;
import com.example.ddellacquaFinal.entity.Paciente;
import com.example.ddellacquaFinal.entity.Turno;
import com.example.ddellacquaFinal.exception.ResourceNotFoundException;
import com.example.ddellacquaFinal.service.OdontologoService;
import com.example.ddellacquaFinal.service.PacienteService;
import com.example.ddellacquaFinal.service.TurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class TurnoServiceTest {

    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    private Turno turno;

    @Autowired
    public TurnoServiceTest(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @Test
    @Order(1)
    public void registrarTurnoTest(){
        Odontologo odontologoAGuardar = odontologoService.guardarOdontologo(new Odontologo("47728891", "Damian", "DellAcqua"));
        Paciente pacienteAGuardar = pacienteService.guardarPaciente(new Paciente( "Roberto", "Carlos",new Domicilio("Camino a la desgracia", 666,"lamentos", "infierno"),"16661666",
        LocalDate.of(2022,07,14)));

        TurnoDTO turnoNuevo = new TurnoDTO();
        turnoNuevo.setFecha(LocalDate.of(2022,12,2));
        turnoNuevo.setOdontologoId(odontologoAGuardar.getId());
        turnoNuevo.setPacienteId(pacienteAGuardar.getId());

        TurnoDTO turnoDTOARegistrar = turnoService.guardarTurno(turnoNuevo);
        Odontologo odotnologoNuevo = odontologoService.guardarOdontologo(new Odontologo("47728891", "Damian", "DellAcqua"));

        assertEquals(1, turnoDTOARegistrar.getId());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorIdTest(){
        Long idABuscar = 1L;
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(idABuscar);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarTurnosTest(){
        List<TurnoDTO> turnos = turnoService.buscarTodosTurno();
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada,turnos.size());
    }

    @Test
    @Order(4)
    public void actualilzarTurnoTest(){

        Odontologo odontologoAGuardar = odontologoService.guardarOdontologo(new Odontologo("47728891", "Damian", "DellAcqua"));
        Paciente pacienteAGuardar = pacienteService.guardarPaciente(new Paciente( "Roberto", "Carlos",new Domicilio("Camino a la desgracia", 666,"lamentos", "infierno"),"16661666",
        LocalDate.of(2022,05,10)));

        TurnoDTO turnoNuevo = new TurnoDTO();
        turnoNuevo.setFecha(LocalDate.of(2022,12,2));
        turnoNuevo.setOdontologoId(odontologoAGuardar.getId());
        turnoNuevo.setPacienteId(pacienteAGuardar.getId());
        TurnoDTO turnoDTOAActualizar = turnoService.guardarTurno(turnoNuevo);
        turnoService.actualizarTurno(turnoDTOAActualizar);
        Optional<TurnoDTO> turnoAActualizado = turnoService.buscarTurno(turnoDTOAActualizar.getId());
        assertEquals(2L, turnoAActualizado.get().getId());
    }

    @Test
    @Order(5)
    public void aliminarTurnoTest() throws ResourceNotFoundException {
        Long idAEliminar = 1L;
        turnoService.eliminarTurno(idAEliminar);
        Optional<TurnoDTO> turnoEliminado = turnoService.buscarTurno(idAEliminar);
        assertFalse(turnoEliminado.isPresent());
    }


}