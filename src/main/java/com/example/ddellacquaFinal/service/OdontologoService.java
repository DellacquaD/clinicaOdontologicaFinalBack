package com.example.ddellacquaFinal.service;
import com.example.ddellacquaFinal.entity.Odontologo;
import com.example.ddellacquaFinal.exception.ResourceNotFoundException;
import com.example.ddellacquaFinal.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);


    OdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService (OdontologoRepository odontologoRepository){
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardarOdontologo (Odontologo odontologo){
        LOGGER.info("Se solicita el guardado de un Odontologo");
        return odontologoRepository.save(odontologo);
    }

    public void actualizarOdontologo (Odontologo odontologo){
        LOGGER.info("Se solicita actualizar el odontonlogo con ID" + odontologo.getId());
        odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo (Long id) throws ResourceNotFoundException {
        LOGGER.warn("Se solicita eliminar el odontonlogo con ID" + id);
        odontologoRepository.deleteById(id);
    }
    public Optional<Odontologo> buscarOdontologo (Long id){
        LOGGER.info("Se solicita actualizar el odontonlogo con ID" + id);
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> listarOdontologos(){
        LOGGER.info("Se solicita el listado de todos los Odontologos" );
        return odontologoRepository.findAll();
    }

}
