package kr.or.connect.reservationManagement.dto;

import java.util.Date;

public class CommentImages {
	private String contentType;
	private Date createDate;
	private Date modifyDate;
	private boolean deleteFlag; 
	private int fileId;
	private String fileName;
	private int ImageId;
	private int reservationInfoId;

	private String SaveFileName;
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
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getImageId() {
		return ImageId;
	}
	public void setImageId(int imageId) {
		ImageId = imageId;
	}
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public String getSaveFileName() {
		return SaveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		SaveFileName = saveFileName;
	}
	
	@Override
	public String toString() {
		return "CommentImages [contentType=" + contentType + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", deleteFlag=" + deleteFlag + ", fileId=" + fileId + ", fileName=" + fileName + ", ImageId="
				+ ImageId + ", reservationInfoId=" + reservationInfoId + 
				", SaveFileName=" + SaveFileName + "]";
	}
}
