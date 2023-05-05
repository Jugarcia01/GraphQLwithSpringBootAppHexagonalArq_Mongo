package com.jgo.demo_graphql.util;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

  private final ModelMapper modelMapper;

  @Autowired
  public MapperUtil (ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public <D> D map(Object source, Class<D> destinationType) {
    return modelMapper.map(source, destinationType);
  }

  public <D> List<D> mapList(List<?> sourceList, Class<D> destinationType) {
    return sourceList.stream().map(entity -> modelMapper.map(entity, destinationType))
        .collect(Collectors.toList());
  }

}
