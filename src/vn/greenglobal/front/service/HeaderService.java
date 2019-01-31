package vn.greenglobal.front.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

import vn.toancauxanh.model.NguoiDung;

public class HeaderService extends FrontService {
	private NguoiDung user;

	public NguoiDung getUser() {
		if (user == null) {
			user = getNguoiDung();
		}
		return user;
	}

	@Command
	public void search() {
		String tuKhoa = MapUtils.getString(argDeco(), "tukhoa");
		if (tuKhoa != null && !tuKhoa.isEmpty()) {
			String param = "";
			param = ("".equals(param) ? "" : param + "&") + (tuKhoa != null ? "tukhoa=" + tuKhoa.trim() : "");
			Executions.sendRedirect("/timkiem?" + param);
		}
	}

	@Override
	public void redirectLogin(HttpServletRequest req, HttpServletResponse res) {
		// K redirect
	}
}
