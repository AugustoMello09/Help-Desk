package io.gitHub.AugustoMello09.helpDesk.controllers.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private LocalDateTime timestemp;
	private Integer status;
	private String error;
	private String path;

	public StandardError() {
	}

	public StandardError(LocalDateTime timestemp, Integer status, String error, String path) {
		super();
		this.timestemp = timestemp;
		this.status = status;
		this.error = error;
		this.path = path;
	}

	public LocalDateTime getTimestemp() {
		return timestemp;
	}

	public void setTimestemp(LocalDateTime timestemp) {
		this.timestemp = timestemp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
