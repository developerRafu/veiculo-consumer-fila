package com.example.demo.resource;

import com.example.demo.models.dtos.VeiculoDto;
import com.example.demo.models.entities.Veiculo;
import com.example.demo.service.VeiculoService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueResource {

    @Autowired
    private VeiculoService service;
    @Autowired
    private ModelMapper mapper;

    @RabbitListener(queues = {"veiculo-fila"})
    public void saveVeiculo(VeiculoDto dto) {
        Veiculo veiculo = this.convertToEntity(dto);
        service.save(veiculo);
    }

    private Veiculo convertToEntity(VeiculoDto dto){
        return this.mapper.map(dto, Veiculo.class);
    }
}
