package com.example.demo.resource;

import com.example.demo.models.dtos.ClienteDto;
import com.example.demo.models.entities.Cliente;
import com.example.demo.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAll(){
        return ResponseEntity.ok(
                service
                .findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getById(@PathVariable Long id){
        return service.findById(id)
                .map(cliente -> {
                    ClienteDto dto = this.convertToDto(cliente);
                    return ResponseEntity.ok(dto);
                })
                .orElseThrow(()->new RuntimeException("Objeto não encontrado"));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> save(@RequestBody ClienteDto dto){
        Cliente cliente = this.convertToEntity(dto);
        cliente = this.service.save(cliente);
        dto = this.convertToDto(cliente);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@RequestBody  ClienteDto dto, @PathVariable Long id){
        dto.setId(id);
        Cliente cliente = this.convertToEntity(dto);
        cliente = this.service.update(cliente);
        dto = this.convertToDto(cliente);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        this.service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private ClienteDto convertToDto(Cliente obj){
        return mapper.map(obj, ClienteDto.class);
    }

    private Cliente convertToEntity(ClienteDto dto){
        return this.mapper.map(dto, Cliente.class);
    }
}
