package com.example.ddellacquaFinal.controller;
import com.example.ddellacquaFinal.entity.Paciente;
import com.example.ddellacquaFinal.exception.ResourceNotFoundException;
import com.example.ddellacquaFinal.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) {
        Optional<Paciente> resultado = pacienteService.buscarPaciente(id);
        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        Optional<Paciente> buscado = pacienteService.buscarPaciente(paciente.getId());
        if (buscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("El paciente con el id: " + paciente.getId() + " fue actualizado");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity <String> eliminarPaciente (@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> resultado = pacienteService.buscarPaciente(id);
        if (resultado.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Se eliminó el paciente correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

