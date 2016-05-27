package com.mindtree.coe.staffmanagement.service;

import java.util.ArrayList;
import java.util.List;

import com.mindtree.coe.staffmanagement.dto.MemberDto;
import com.mindtree.coe.staffmanagement.entity.Member;

public class DtoBuilder {

	public MemberDto getMemberDto(Member member){
		MemberDto memberDto = new MemberDto();
		memberDto.setId(member.getId());
		memberDto.setName(member.getName());
		memberDto.setAge(member.getAge());
		memberDto.setCompetency(member.getCompetency());
		memberDto.setGender(member.getGender());
		memberDto.setMemberType(member.getMemberType());
		memberDto.setMid(member.getMid());
		
		return memberDto;
		
	}
	
	public List<MemberDto> getMemberDtoList(List<Member> memberList){
		List<MemberDto> memberDtoList = new ArrayList<MemberDto>();
		if(!(memberList==null)){
		for(Member member : memberList){
			MemberDto memberDto = getMemberDto(member);
			memberDtoList.add(memberDto);
		}
		return memberDtoList;
		}
		else{
			return null;
		}
		
	}

}
