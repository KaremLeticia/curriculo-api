package com.example.curriculo_api.service;

import com.example.curriculo_api.model.Curriculo;
import com.example.curriculo_api.repository.CurriculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;

    public void insertCurriculo(Curriculo curriculo) {
        curriculoRepository.save(curriculo);
    }

    public List<Curriculo> getCurriculos() {
        return curriculoRepository.findAll();
    }

    public Curriculo getCurriculo(Long id) {
        return curriculoRepository.findById(id).orElse(null);
    }

    public void updateCurriculo(Long id, Curriculo curriculo) {
        Curriculo existingCurriculo = curriculoRepository.findById(id).orElse(null);
        if (existingCurriculo != null) {
            existingCurriculo.setNome(curriculo.getNome());
            existingCurriculo.setIdade(curriculo.getIdade());
            existingCurriculo.setEmail(curriculo.getEmail());
            existingCurriculo.setTelefone(curriculo.getTelefone());
            existingCurriculo.setInformacoes(curriculo.getInformacoes());

            curriculoRepository.save(existingCurriculo);
        }
    }

    public void deleteCurriculo(Long id) {
        curriculoRepository.deleteById(id);
    }
}
