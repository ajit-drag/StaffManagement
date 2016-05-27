package com.mindtree.coe.staffmanagement.interfaces.service;

import com.mindtree.coe.staffmanagement.entity.MemberType;
import com.mindtree.coe.staffmanagement.exceptions.ServiceException;

public interface Service {
	Object getAllMembers() throws ServiceException;
	Object getMemberTypeWise(MemberType memberType) throws ServiceException;
}
