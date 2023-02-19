package study.invoice.service.Mapping;

import org.modelmapper.ModelMapper;
import java.util.*;
public class MappingHelper {
    public static <S, D> D map(S s, Class<D> classType) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(s, classType);
    }

    public static <S, D> List<D> map(List<S> s, Class<D> classType) {
        ModelMapper modelMapper = new ModelMapper();
        return s.stream().map(item -> modelMapper.map(item, classType)).toList();
    }
}
