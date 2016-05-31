package com.mindtree.coe.staffmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique = true)
	private String mid;
	@Column
	private String name;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Enumerated(EnumType.STRING)
	private MemberType memberType;
	@Enumerated(EnumType.STRING)
	private Competency competency;
	@Column
	private int age;

	public Member() {
		super();
	}
	

	public Member(String mid, String name, Gender gender, MemberType memberType,
			Competency competency, int age) {
		super();
		this.mid = mid;
		this.name = name;
		this.gender = gender;
		this.memberType = memberType;
		this.competency = competency;
		this.age = age;
	}


	public MemberType getMemberType() {
		return memberType;
	}


	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}


	public Member(long id, String mid, String name, Gender gender,
			com.mindtree.coe.staffmanagement.entity.MemberType memberType, Competency competency, int age) {
		super();
		this.id = id;
		this.mid = mid;
		this.name = name;
		this.gender = gender;
		this.memberType = memberType;
		this.competency = competency;
		this.age = age;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Competency getCompetency() {
		return competency;
	}

	public void setCompetency(Competency competency) {
		this.competency = competency;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "Member [id=" + id + ", mid=" + mid + ", name=" + name + ", gender=" + gender + ", memberType="
				+ memberType + ", competency=" + competency + ", age=" + age + "]";
	}
	
}
