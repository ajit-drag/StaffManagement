package com.mindtree.coe.staffmanagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.coe.staffmanagement.dto.ErrorDto;
import com.mindtree.coe.staffmanagement.dto.MemberDto;
import com.mindtree.coe.staffmanagement.entity.ErrorInfo;
import com.mindtree.coe.staffmanagement.entity.MemberType;
import com.mindtree.coe.staffmanagement.exceptions.NoResultException;
import com.mindtree.coe.staffmanagement.exceptions.ServiceException;
import com.mindtree.coe.staffmanagement.interfaces.service.Service;
import com.mindtree.coe.staffmanagement.service.ServiceImpl;

@Controller
@ControllerAdvice
public class RestController {
	Service serviceImpl;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/staff", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStaff() throws ServiceException, JsonProcessingException, NoResultException {

		List<MemberDto> memberList = null;
		ErrorDto errorDto;
		Object allMembers;
		ObjectMapper mapper = new ObjectMapper();
		serviceImpl = new ServiceImpl();
		allMembers = serviceImpl.getAllMembers();
		if (allMembers instanceof List<?>) {
			memberList = (List<MemberDto>) allMembers;
			if (memberList.isEmpty()) {
				throw new NoResultException("Query does not have any result associated with it.");
			} else {
				return new ResponseEntity<String>(mapper.writeValueAsString(memberList), HttpStatus.OK);
			}

		} else {
			errorDto = (ErrorDto) allMembers;
			return new ResponseEntity<String>(mapper.writeValueAsString(errorDto), HttpStatus.OK);
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/staff/{memberType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStaffTypeWise(@PathVariable("memberType") MemberType memberType)
			throws ServiceException, JsonProcessingException, NoResultException {
		System.out.println(memberType);
		List<MemberDto> memberList = null;
		ErrorDto errorDto;
		Object allMembers;
		ObjectMapper mapper = new ObjectMapper();
		serviceImpl = new ServiceImpl();
		allMembers = serviceImpl.getMemberTypeWise(memberType);
		System.out.println(allMembers instanceof List<?>);
		if (allMembers instanceof List<?>) {
			memberList = (List<MemberDto>) allMembers;
			System.out.println(memberList.isEmpty());
			if (memberList.isEmpty()) {
				throw new NoResultException("Query does not have any result associated with it.");
			} else {
				return new ResponseEntity<String>(mapper.writeValueAsString(memberList), HttpStatus.OK);
			}

		} else {
			errorDto = (ErrorDto) allMembers;
			return new ResponseEntity<String>(mapper.writeValueAsString(errorDto), HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/staff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addMemeber(@RequestBody MemberDto memberDto, UriComponentsBuilder ucBuilder,
			@RequestParam(value = "update", defaultValue = "false") boolean update)
					throws ServiceException,  NoResultException, JsonProcessingException {
		serviceImpl = new ServiceImpl();
		MemberDto updatedMember;
		ObjectMapper mapper = new ObjectMapper();
		if (memberDto.getMid() != null) {
			boolean memberExist = serviceImpl.memberExist(memberDto.getMid());
			System.out.println("Inserting" + memberDto);
			System.out.println("Member Exists: " + memberExist);
			System.out.println("Update Allowed: " + update);

			if (memberExist == true && update == false) {
				return new ResponseEntity<String>(
						"{\"error\":\"A record with mid= " + memberDto.getMid()
								+ " already exist.Please use parameter update=true to update the prexisting record.\"}",
						HttpStatus.CONFLICT);
			} else {

				updatedMember = serviceImpl.createOrUpdate(memberDto);
				return new ResponseEntity<String>(mapper.writeValueAsString(updatedMember), HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<String>(
					"{\"error\":\"MID is required to create or update records. Please provide a valid MID.\"}",
					HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/staff/{mid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteMemeber(@PathVariable("mid") String mid)throws ServiceException, JsonProcessingException, NoResultException {
		serviceImpl = new ServiceImpl();
		boolean memberExist = serviceImpl.memberExist(mid);
		if(memberExist){
			serviceImpl.deleteMember(mid);
			return new ResponseEntity<String>(
					"{\"status\":\"Record with mid= " + mid
							+ " deleted successfully.\"}",
					HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity<String>("{\"error\":\"Can not delete record as given MID is invalid or not present.\"}",HttpStatus.NOT_FOUND);
		}		
	}
	

	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
		String errorMessage = ex.getValue() + " catagory does not exist.";
		return new ErrorInfo(errorMessage);
	}

	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorInfo handleServiceException(HttpServletRequest req, ServiceException ex) {
		String errorMessage = ex.getMessage();
		return new ErrorInfo(errorMessage);
	}

	@ExceptionHandler(JsonProcessingException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorInfo handleJsonProcessingException(HttpServletRequest req, JsonProcessingException ex) {
		String errorMessage = ex.getMessage();
		return new ErrorInfo(errorMessage);
	}

	@ExceptionHandler(NoResultException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleNoResultException(HttpServletRequest req, NoResultException ex) {
		System.out.println(ex.getMessage());
		String errorMessage = ex.getMessage();
		return new ErrorInfo(errorMessage);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleNoHandlerFoundException(HttpServletRequest req, NoHandlerFoundException ex) {
		String errorMessage = "This url is not a valid url.";
		return new ErrorInfo(errorMessage);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorInfo handleHttpRequestMethodNotSupportedException(HttpServletRequest req, HttpRequestMethodNotSupportedException ex) {
		String errorMessage = ex.getMessage();
		return new ErrorInfo(errorMessage);
	}

}
