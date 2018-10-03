package com.acis.fileupload.form;

import org.springframework.web.multipart.MultipartFile;

public class MyUploadForm {

	private String description;

	// Upload files.
	private MultipartFile[] fileDatas;

	public MultipartFile[] getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(MultipartFile[] fileDatas) {
		this.fileDatas = fileDatas;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}