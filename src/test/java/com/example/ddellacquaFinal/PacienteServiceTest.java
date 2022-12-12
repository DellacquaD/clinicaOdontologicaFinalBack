package com.example.ddellacquaFinal;
import com.example.ddellacquaFinal.entity.Domicilio;
import com.example.ddellacquaFinal.entity.Paciente;
import com.example.ddellacquaFinal.exception.ResourceNotFoundException;
import com.example.ddellacquaFinal.service.PacienteService;
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
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Paciente pacienteAGuardar = new Paciente( "Damian", "DellAcqua",new Domicilio("LuisBatlleBerres", 6000,"Montevideo", "Montevideo"),"47728891",
                LocalDate.of(2022,12,10));

        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(1L,pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorIdTest(){
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);
        assertNotNull(pacienteBuscado.get());
    }
    @Test
    @Order(3)
    public void buscarPacientesTest(){
        List<Paciente> pacientes = pacienteService.listarPacientes();
        //por la cantidad de los pacientes
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada,pacientes.size());
    }
    @Test
    @Order(4)
    public void actualizarPacienteTest(){
        Paciente pacienteActualizar = new Paciente( 1L, "Damian", "DellAcqua",new Domicilio("Luis Batlle Berres", 6000,"Montevideo", "Montevideo"),"47728891",
                LocalDate.of(2022,05,10));
        pacienteService.actualizarPaciente(pacienteActualizar);
        Optional<Paciente> pacienteActualizado = pacienteService.buscarPaciente(pacienteActualizar.getId());
        assertEquals("Damian",pacienteActualizado.get().getNombre());
    }
    @Test
    @Order(5)
    public void eliminarPacienteTest()throws ResourceNotFoundException {
        Long idAEliminar = 1L;
        pacienteService.eliminarPaciente(idAEliminar);
        Optional<Paciente> pacienteEliminado=pacienteService.buscarPaciente(idAEliminar);
        assertFalse(pacienteEliminado.isPresent());
    }

}