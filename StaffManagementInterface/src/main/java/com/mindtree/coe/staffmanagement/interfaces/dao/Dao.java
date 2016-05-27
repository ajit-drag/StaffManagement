package com.mindtree.coe.staffmanagement.interfaces.dao;

import java.util.List;

import com.mindtree.coe.staffmanagement.entity.Member;
import com.mindtree.coe.staffmanagement.entity.MemberType;
import com.mindtree.coe.staffmanagement.exceptions.DaoException;

public interface Dao {
	
	public List<Member> getAllMembers() throws DaoException;
	public List<Member> getMemberTypeWise(MemberType memberType) throws DaoException;
}
