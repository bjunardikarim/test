package id.apps.app.model;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entrydata")
public class EntryData {

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "user_nm")
	private String userNm;
	
	@Column(name = "poc")
	private String poc;
	
	@Column(name = "date_input")
	private String dateipt;
	
	@Column(name = "file_nm")
	private String fileNm;
	
	@Column(name = "file_loc")
	private String fileLoc;
	
	@Column(name = "file_nm_old")
	private String fileNmOld;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getPoc() {
		return poc;
	}

	public void setPoc(String poc) {
		this.poc = poc;
	}

	public String getDateipt() {
		return dateipt;
	}

	public void setDateipt(String dateipt) {
		this.dateipt = dateipt;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getFileLoc() {
		return fileLoc;
	}

	public void setFileLoc(String fileLoc) {
		this.fileLoc = fileLoc;
	}

	public String getFileNmOld() {
		return fileNmOld;
	}

	public void setFileNmOld(String fileNmOld) {
		this.fileNmOld = fileNmOld;
	}

}
