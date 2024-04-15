package org.fullstack4.chap01.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
    INSTANCE;

    private ModelMapper modelMapper;

    MapperUtil(){
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT).setFieldMatchingEnabled(true);

    }

    public ModelMapper getModelMapper(){
        return modelMapper;
    }
}
