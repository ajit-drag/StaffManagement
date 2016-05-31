package com.mindtree.coe.staffmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mindtree.coe.staffmanagement.entity.Competency;
import com.mindtree.coe.staffmanagement.entity.Gender;
import com.mindtree.coe.staffmanagement.entity.MemberType;

@JsonPropertyOrder({ "id", "mid", "name", "competency", "member-type", "gender", "age" })
public class MemberDto {

	@JsonProperty("id")
	private long id;
	@JsonProperty("mid")
	private String mid;
	@JsonProperty("name")
	private String name;
	@JsonProperty("gender")
	private Gender gender;
	@JsonProperty("competency")
	private Competency competency;
	@JsonProperty("age")
	private int age;
	@JsonProperty("member-type")
	public MemberType memberType;

	public MemberDto() {
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

	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	@Override
	public String toString() {
		return "Member: [mid=" + mid + ", name=" + name + ", gender=" + gender + ", competency=" + competency
				+ ", age=" + age + ", memberType=" + memberType + "]";
	}

}
