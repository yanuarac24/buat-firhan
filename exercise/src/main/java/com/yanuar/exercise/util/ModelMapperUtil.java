package com.yanuar.exercise.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtil {

    public ModelMapper modelMapperUtil() {
        return new ModelMapper();
    }
}
