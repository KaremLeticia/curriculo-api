package com.example.curriculo_api.controller;

import com.example.curriculo_api.model.Curriculo;
import com.example.curriculo_api.service.CurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/curriculos")
public class CurriculoController {

    @Autowired
    private CurriculoService curriculoService;

    @PostMapping
    public ResponseEntity<?> postCurriculo(@RequestBody Curriculo curriculo) {
        if (curriculo == null || curriculo.getNome() == null || curriculo.getIdade() == null ||
                curriculo.getEmail() == null || curriculo.getTelefone() == null || curriculo.getInformacoes() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: preencha todos os campos obrigatórios (nome, idade, email, telefone, informacoes)");
        }
        curriculoService.insertCurriculo(curriculo);
        return ResponseEntity.ok().body(Map.of("sucesso", true, "criado", 1));
    }

    @GetMapping
    public ResponseEntity<?> getCurriculos() {
        return ResponseEntity.ok().body(curriculoService.getCurriculos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCurriculo(@PathVariable Long id) {
        return ResponseEntity.ok().body(curriculoService.getCurriculo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCurriculo(@PathVariable Long id, @RequestBody Curriculo curriculo) {
        if (id == null || curriculo == null || curriculo.getNome() == null || curriculo.getIdade() == 0 ||
                curriculo.getEmail() == null || curriculo.getTelefone() == null || curriculo.getInformacoes() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: preencha todos os campos obrigatórios (nome, idade, email, telefone, informacoes)");
        }
        curriculoService.updateCurriculo(id, curriculo);
        return ResponseEntity.ok().body(Map.of("sucesso", true, "atualizados", 1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurriculo(@PathVariable Long id) {
        curriculoService.deleteCurriculo(id);
        return ResponseEntity.ok().body(Map.of("sucesso", true, "removidos", 1));
    }
}