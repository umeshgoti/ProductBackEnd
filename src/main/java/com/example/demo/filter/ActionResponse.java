package com.example.demo.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_EMPTY)
@Getter
@Setter
public class ActionResponse {

	private Integer status;
	private String message;
	private Object data;
	private String error;
	private String path;

	@JsonIgnore
	private HttpStatus httpStatus;

	public ActionResponse(Integer status, String message, HttpStatus httpStatus) {
		this.status = status;
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public ActionResponse(Integer status, String message, Object data, HttpStatus httpStatus) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.httpStatus = httpStatus;
	}

	public static ResponseEntity<Object> getResponse(String message, HttpStatus httpStatus,
			HttpServletRequest httpServletRequest) {

		ActionResponse actionResponse = new ActionResponse(httpStatus.value(), message, httpStatus);
		actionResponse.setPath(
				httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString());

		return new ResponseEntity<>(actionResponse, actionResponse.getHttpStatus());
	}
	
	public static ResponseEntity<Object> getResponse(String message, Object data, HttpStatus httpStatus,
			HttpServletRequest httpServletRequest) {

		ActionResponse actionResponse = new ActionResponse(httpStatus.value(), message, data, httpStatus);
		actionResponse.setPath(
				httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString());

		return new ResponseEntity<>(actionResponse, actionResponse.getHttpStatus());
	}
	
	

}
