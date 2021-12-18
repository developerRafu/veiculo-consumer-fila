package com.example.demo.resource;

import com.example.demo.models.dtos.VeiculoDto;
import com.example.demo.models.entities.Veiculo;
import com.example.demo.service.VeiculoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Veiculos")
public class VeiculoResource {

    @Autowired
    private VeiculoService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<VeiculoDto>> getAll(){
        return ResponseEntity.ok(
                service
                .findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDto> getById(@PathVariable Long id){
        return service.findById(id)
                .map(Veiculo -> {
                    VeiculoDto dto = this.convertToDto(Veiculo);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(()->new RuntimeException("Objeto não encontrado"));
    }

    @PostMapping
    public ResponseEntity<VeiculoDto> save(VeiculoDto dto){
        Veiculo Veiculo = this.convertToEntity(dto);
        Veiculo = this.service.save(Veiculo);
        dto = this.convertToDto(Veiculo);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDto> update(@RequestBody  VeiculoDto dto, @PathVariable Long id){
        dto.setId(id);
        Veiculo Veiculo = this.convertToEntity(dto);
        Veiculo = this.service.update(Veiculo);
        dto = this.convertToDto(Veiculo);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        this.service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private VeiculoDto convertToDto(Veiculo obj){
        return mapper.map(obj, VeiculoDto.class);
    }

    private Veiculo convertToEntity(VeiculoDto dto){
        return this.mapper.map(dto, Veiculo.class);
    }
}
