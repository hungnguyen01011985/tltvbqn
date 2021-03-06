package vn.toancauxanh.cms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.PhongBan;
import vn.toancauxanh.model.QPhongBan;
import vn.toancauxanh.service.BasicService;
import vn.toancauxanh.service.ExcelUtil;

public class PhongBanService extends BasicService<PhongBan> {
	
	private PhongBan phongBanSelected;
	
	public PhongBan getPhongBanSelected() {
		return phongBanSelected;
	}

	public void setPhongBanSelected(PhongBan phongBanSelected) {
		this.phongBanSelected = phongBanSelected;
		BindUtils.postNotifyChange(null, null, this, "listNhanVienTheoPhongBan");
	}

	public JPAQuery<PhongBan> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		JPAQuery<PhongBan> q = find(PhongBan.class);

		if (param != null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%";
			q.where(QPhongBan.phongBan.ten.like(tuKhoa));
		}

		q.orderBy(QPhongBan.phongBan.ngaySua.desc());
		return q;
	}

	public List<PhongBan> getAllListPhongBan() {
		List<PhongBan> list = new ArrayList<>();
		JPAQuery<PhongBan> q = find(PhongBan.class).orderBy(QPhongBan.phongBan.ngaySua.desc());
		list.addAll(q.fetch());
		return list;
	}

	public List<PhongBan> getListPhongBanAndNull() {
		List<PhongBan> list = new ArrayList<>();
		list.add(null);
		list.addAll(getAllListPhongBan());
		return list;
	}
	


	@Command
	public void xuatExcel(@BindingParam("query") final JPAQuery<PhongBan> query,
			@BindingParam("title") final String title) throws IOException {
		ExcelUtil.exportDanhSachPhongBan("danhSachPhongBan", "danhSach",
				query.orderBy(QPhongBan.phongBan.ten.asc()).fetch(), title);
	}
}
