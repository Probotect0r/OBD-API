package com.missingplatform.obdapi.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Drive {
	@Id
	String id;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Date start;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Date end;

	public Drive() {
		this.start = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}
