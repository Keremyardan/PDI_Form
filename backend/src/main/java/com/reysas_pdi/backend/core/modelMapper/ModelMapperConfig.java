package com.reysas_pdi.backend.core.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean(name="modelMapper")
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
