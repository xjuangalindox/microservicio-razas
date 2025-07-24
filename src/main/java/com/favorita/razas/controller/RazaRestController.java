package com.favorita.razas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favorita.razas.controller.dto.RazaDTO;
import com.favorita.razas.service.IRazaService;

@RestController
@RequestMapping("/api/razas")
public class RazaRestController {
    
    @Autowired
    private IRazaService razaService;

    @GetMapping
    public ResponseEntity<List<RazaDTO>> obtenerRazas(){
        List<RazaDTO> razas = razaService.obtenerRazas();

        if(razas.isEmpty()){
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(razas); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<RazaDTO> obtenerRazaPorId(@PathVariable Long id){
        Optional<RazaDTO> razaOpt = razaService.obtenerRazaPorId(id);
        if(razaOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(razaOpt.get());
    }

    @PostMapping
    public ResponseEntity<RazaDTO> guardarRaza(@RequestBody RazaDTO razaDTO){
        RazaDTO nuevo = razaService.guardarRaza(razaDTO);
        return new ResponseEntity<RazaDTO>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RazaDTO> editarRaza(@PathVariable Long id, @RequestBody RazaDTO razaDTO){
        return ResponseEntity.ok(razaService.editarRaza(id, razaDTO));
        // RazaDTO modificado = razaService.editarRaza(id, razaDTO);
        // return ResponseEntity.ok(modificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRazaPorId(@PathVariable Long id){
        boolean eliminado = razaService.eliminarRazaPorId(id);

        if(eliminado){
            return ResponseEntity.noContent().build(); // No Content
        }
        return ResponseEntity.notFound().build(); // No found
    }

}
