package org.dmle.userfood.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImpl implements MapperService{
    @Override
    public <T> T updateValues(T to, Object from) {
        try {
            return (new ObjectMapper()).updateValue(to, from);
        } catch (JsonMappingException ignored) {}

        throw new IllegalArgumentException("Wrong value.");
    }
}
