package com.bakos.UserDTO;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class UploadForm implements Serializable{


	private static final long serialVersionUID = 1L;
	
	
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	private String napis;

	public String getNapis() {
		return napis;
	}

	public void setNapis(String napis) {
		this.napis = napis;
	}
	
}
