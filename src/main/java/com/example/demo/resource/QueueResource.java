package com.example.demo.resource;

import com.example.demo.models.dtos.VeiculoDto;
import com.example.demo.models.entities.Veiculo;
import com.example.demo.service.VeiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QueueResource {

    @Autowired
    private VeiculoService service;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"veiculo-fila"})
    public void saveVeiculo(String message) {
        try {
            log.info("@MESSAGE - INCLUINDO VÉICULO");
            VeiculoDto dto = objectMapper.readValue(message, VeiculoDto.class);
            Veiculo veiculo = this.convertToEntity(dto);
            service.save(veiculo);
            log.info("@MESSAGE - VÉICULO INCLUÍDO");
        }catch (Exception ex){
            log.error("@MESSAGE - ERRO AO INCLUIR VEÍCULO " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    private Veiculo convertToEntity(VeiculoDto dto){
        return this.mapper.map(dto, Veiculo.class);
    }
}
