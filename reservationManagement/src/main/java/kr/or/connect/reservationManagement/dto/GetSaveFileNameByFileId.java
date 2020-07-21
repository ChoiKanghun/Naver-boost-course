package kr.or.connect.reservationManagement.dto;

public class GetSaveFileNameByFileId {
	private String saveFileName;

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "GetSaveFileNameByReservationUserCommentImageId [saveFileName=" + saveFileName + "]";
	}
}

