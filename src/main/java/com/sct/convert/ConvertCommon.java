package com.sct.convert;

import org.modelmapper.ModelMapper;

public abstract class ConvertCommon <E,D> {
	public ModelMapper  modelMapper = new ModelMapper();
	
	public abstract E covertEntity(D d); 
	
	public abstract D convertDto(E e);
}
