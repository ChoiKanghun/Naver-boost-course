package kr.or.connect.reservationManagement.dto;

import java.util.Date;

public class DisplayInfoImage {
	private String contentType;
	private Date createDate;
	private Date modifyDate;
	private boolean deleteFlag;
	private String fileName;
	private int fileId;
	private int displayInfoId;
	private int displayInfoImageId;
	private String saveFileName;
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public int getDisplayInfoImageId() {
		return displayInfoImageId;
	}
	public void setDisplayInfoImageId(int displayInfoImageId) {
		this.displayInfoImageId = displayInfoImageId;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	
	@Override
	public String toString() {
		return "DisplayInfoImage [contentType=" + contentType + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", deleteFlag=" + deleteFlag + ", fileName=" + fileName + ", fileId=" + fileId
				+ ", displayInfoId=" + displayInfoId + ", displayInfoImageId=" + displayInfoImageId + ", saveFileName="
				+ saveFileName + "]";
	}
}
