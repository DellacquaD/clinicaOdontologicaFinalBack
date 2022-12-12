package com.example.ddellacquaFinal.controller;
import com.example.ddellacquaFinal.entity.Odontologo;
import com.example.ddellacquaFinal.exception.ResourceNotFoundException;
import com.example.ddellacquaFinal.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos() {
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) {
        Optional<Odontologo> resultado = odontologoService.buscarOdontologo(id);
        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> buscado = odontologoService.buscarOdontologo(odontologo.getId());
        if (buscado.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("El odontologo con el id: " + odontologo.getId() + " fue actualizado");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity <String> eliminarOdontologo (@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> resultado = odontologoService.buscarOdontologo(id);
        if (resultado.isPresent()) {
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Se eliminó el odontologo correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}