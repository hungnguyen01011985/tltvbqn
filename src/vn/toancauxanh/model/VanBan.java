package vn.toancauxanh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name="vanban")
public class VanBan extends Model<VanBan>{
	
	private String code;
	@Size(max=500)
	private String subject;
	private String documentId;
	private String docId;
	private String loaiLienThong;
	private String fileUrl;
	private Date ngayPhatHanh;
	private String soKyhieu;
	private int soFileDinhKem;
	private String checkSum;
	private CoQuanToChuc coQuanToChuc;

	public VanBan() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getLoaiLienThong() {
		return loaiLienThong;
	}

	public void setLoaiLienThong(String loaiLienThong) {
		this.loaiLienThong = loaiLienThong;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Date getNgayPhatHanh() {
		return ngayPhatHanh;
	}

	public void setNgayPhatHanh(Date date) {
		this.ngayPhatHanh = date;
	}

	public String getSoKyhieu() {
		return soKyhieu;
	}

	public void setSoKyhieu(String soKyhieu) {
		this.soKyhieu = soKyhieu;
	}

	public int getSoFileDinhKem() {
		return soFileDinhKem;
	}

	public void setSoFileDinhKem(int soFileDinhKem) {
		this.soFileDinhKem = soFileDinhKem;
	}

	public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}
	
	@ManyToOne
	public CoQuanToChuc getCoQuanToChuc() {
		return coQuanToChuc;
	}

	public void setCoQuanToChuc(CoQuanToChuc coQuanToChuc) {
		this.coQuanToChuc = coQuanToChuc;
	}


	
}
