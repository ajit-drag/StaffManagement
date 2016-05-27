package com.mindtree.coe.staffmanagement.service;

import com.mindtree.coe.staffmanagement.daoimpl.DaoImpl;
import com.mindtree.coe.staffmanagement.dto.ErrorDto;
import com.mindtree.coe.staffmanagement.entity.MemberType;
import com.mindtree.coe.staffmanagement.exceptions.DaoException;
import com.mindtree.coe.staffmanagement.exceptions.ServiceException;
import com.mindtree.coe.staffmanagement.interfaces.dao.Dao;
import com.mindtree.coe.staffmanagement.interfaces.service.Service;

public class ServiceImpl implements Service {

	Dao daoImpl;
	public ErrorDto errorDto;

	public ServiceImpl() throws ServiceException {
		try {
			daoImpl = new DaoImpl();
		} catch (DaoException daoException) {
			throw new ServiceException(daoException.getMessage(), daoException);
		}
	}

	@Override
	public Object getAllMembers(){
		DtoBuilder dtoBuilder = new DtoBuilder();
		try {
			return  dtoBuilder.getMemberDtoList(daoImpl.getAllMembers());
		} catch (DaoException daoException) {
			errorDto = new ErrorDto();
			errorDto.setErrorMessage(daoException.getMessage());
			return errorDto;
		}
	}

	@Override
	public Object getMemberTypeWise(MemberType memberType) throws ServiceException {
		DtoBuilder dtoBuilder = new DtoBuilder();
		try {
			return  dtoBuilder.getMemberDtoList(daoImpl.getMemberTypeWise(memberType));
		} catch (DaoException daoException) {
			errorDto = new ErrorDto();
			errorDto.setErrorMessage(daoException.getMessage());
			return errorDto;
		}
	}

}
