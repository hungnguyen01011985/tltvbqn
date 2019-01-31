package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vn.toancauxanh.model.Model;

@Entity
@Table(name = "vanbanden")
public class VanBanDen extends Model<VanBanDen>{

	private boolean daNhan;
	private VanBan vanBan;
	private CoQuanToChuc coQuanToChuc;
	
	public VanBanDen() {
		super();
	}

	public VanBanDen(boolean daNhan, VanBan vanBan, CoQuanToChuc coQuanToChuc) {
		super();
		this.daNhan = daNhan;
		this.vanBan = vanBan;
		this.coQuanToChuc = coQuanToChuc;
	}


	public boolean isDaNhan() {
		return daNhan;
	}

	public void setDaNhan(boolean daNhan) {
		this.daNhan = daNhan;
	}
	
	@ManyToOne
	public VanBan getVanBan() {
		return vanBan;
	}

	public void setVanBan(VanBan vanBan) {
		this.vanBan = vanBan;
	}
	
	@ManyToOne
	public CoQuanToChuc getCoQuanToChuc() {
		return coQuanToChuc;
	}

	public void setCoQuanToChuc(CoQuanToChuc coQuanToChuc) {
		this.coQuanToChuc = coQuanToChuc;
	}
	
	
}
