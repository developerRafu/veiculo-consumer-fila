package com.example.demo.service;

import com.example.demo.models.entities.Cliente;
import com.example.demo.models.entities.Veiculo;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VeiculoService implements BaseService<Veiculo> {

    @Autowired
    private VeiculoRepository repository;
    @Autowired
    private ClienteService clienteService;

    @Override
    public Optional<Veiculo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Veiculo> findAll() {
        return repository.findAll();
    }

    @Override
    public Veiculo save(Veiculo obj) {
        Cliente cliente = this.clienteService.findById(obj.getDono().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return repository.save(obj);
    }

    @Override
    public Veiculo update(Veiculo obj) {
        this.findById(obj.getId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return repository.save(obj);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
