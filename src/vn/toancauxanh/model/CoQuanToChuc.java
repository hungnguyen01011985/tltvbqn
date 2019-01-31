package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vn.toancauxanh.model.Model;

@Entity
@Table(name = "coquantochuc")
public class CoQuanToChuc extends Model<CoQuanToChuc> {
	
	private String organId;
	private String organizationCharge;
	private String organName;
	private String organAdd;
	private String email;
	private String telephone;
	private String fax;
	private String website;
	private boolean noiBo;
	private CoQuanToChuc cha;
	public CoQuanToChuc() {
		super();
	}
	
	@ManyToOne
	public CoQuanToChuc getCha() {
		return cha;
	}
	public void setCha(CoQuanToChuc cha) {
		this.cha = cha;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getOrganizationCharge() {
		return organizationCharge;
	}
	public void setOrganizationCharge(String organizationCharge) {
		this.organizationCharge = organizationCharge;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getOrganAdd() {
		return organAdd;
	}
	public void setOrganAdd(String organAdd) {
		this.organAdd = organAdd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public boolean isNoiBo() {
		return noiBo;
	}
	public void setNoiBo(boolean noiBo) {
		this.noiBo = noiBo;
	}
	
}
