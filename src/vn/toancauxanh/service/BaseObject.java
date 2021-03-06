package vn.toancauxanh.service;

import java.text.Normalizer;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.persistence.Transient;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.SystemPropertyUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Default;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.querydsl.jpa.impl.JPAQuery;

import vn.greenglobal.core.CoreObject;
import vn.toancauxanh.cms.service.HomeService;
import vn.toancauxanh.gg.model.enums.LoaiCongViec;
import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;
import vn.toancauxanh.model.NguoiDung;
import vn.toancauxanh.model.QNguoiDung;
import vn.toancauxanh.model.Setting;
import vn.toancauxanh.sso.Utils;

public class BaseObject<T> extends CoreObject<T> {

	private int first = 0;
	private int last = 0;

	@Transient
	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	@Transient
	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public boolean live = true;

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	@Override
	public Map<Object, Object> getArg() {
		Map<Object, Object> arg = super.getArg();
		return arg;
	}

	public NguoiDung fetchNguoiDung(boolean saving) {
		if (Executions.getCurrent() == null) {
			return null;
		}
		return getNguoiDung(saving, (HttpServletRequest) Executions.getCurrent().getNativeRequest(),
				(HttpServletResponse) Executions.getCurrent().getNativeResponse());
	}

	public NguoiDung getNguoiDung() {
		return fetchNguoiDung(false);
	}

	public NguoiDung getNguoiDung(boolean isSave, HttpServletRequest req, HttpServletResponse res) {
		NguoiDung nguoiDung = null;
		String key = getClass() + "." + NguoiDung.class;
		nguoiDung = (NguoiDung) req.getAttribute(key);
		if (nguoiDung== null || nguoiDung.noId()) {
			Object token = null;
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("email".equals(c.getName())) {
						token = c.getValue();
						break;
					}
				}
			}
			if (token == null) {
				HttpSession ses = req.getSession();
				token = ses.getAttribute("email");
			}
			if (token != null) {
				String[] parts = new String(Base64.decodeBase64(token.toString())).split(":");
				NguoiDung nguoiDungLogin = em().find(NguoiDung.class, NumberUtils.toLong(parts[0], 0));
				if (parts.length == 3 && nguoiDungLogin != null) {
					long expire = NumberUtils.toLong(parts[1], 0);
					if (expire > System.currentTimeMillis() && token.equals(nguoiDungLogin.getCookieToken(expire))) {
						nguoiDung = nguoiDungLogin;
					}
				}
			}
			if (!isSave && (nguoiDung == null)) {
				if (nguoiDung == null) {
					bootstrapNhanVien();
				}
				nguoiDung = new NguoiDung();
				if (token != null) {
					req.getSession().removeAttribute("email");
				}
				redirectLogin(req, res);
			}
			req.setAttribute(key, nguoiDung);
		}

		return isSave && nguoiDung != null && nguoiDung.noId() ? null : nguoiDung;
	}

	public void setActivePage(int value) {
		getArg().put(SystemPropertyUtils.resolvePlaceholders(PH_KEYPAGE), value + 1);
	}

	public <A> JPAQuery<A> page1(JPAQuery<A> q) {
		String kPage = SystemPropertyUtils.resolvePlaceholders(PH_KEYPAGE);
		int len = MapUtils.getIntValue(getArg(), SystemPropertyUtils.resolvePlaceholders(PH_KEYPAGESIZE));
		int page = Math.max(0, MapUtils.getIntValue(getArg(), kPage) - 1);
		if (q.fetchCount() <= page * len) {
			getArg().put(kPage, page = 0);
			BindUtils.postNotifyChange(null, null, getArg(), kPage);
		}
		return q.offset(page * len).limit(len);
	}

	public <A> JPAQuery<A> pageVideo(JPAQuery<A> q) {
		int len = 9;
		String kPage = SystemPropertyUtils.resolvePlaceholders(PH_KEYPAGE);
		int page = Math.max(0, MapUtils.getIntValue(getArg(), kPage) - 1);
		if (q.fetchCount() <= page * len) {
			getArg().put(kPage, page = 0);
			BindUtils.postNotifyChange(null, null, getArg(), kPage);
		}
		return q.offset(page * len).limit(len);
	}

	@Command
	public final void cmd(@BindingParam("ten") @Default(value = "") final String ten,
			@BindingParam("notify") Object beanObject, @BindingParam("attr") @Default(value = "*") String fields) {
		invoke(null, ten, null, beanObject, fields, null, false);
	}

	public String removeAccent(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

	@Command
	public final void cmdLoadPageFrontEnd(@BindingParam("ten") @Default(value = "") final String ten,
			@BindingParam("notify") Object beanObject, @BindingParam("attr") @Default(value = "*") String fields) {
		invoke(null, ten, null, beanObject, "detail", null, false);
	}

	@Transient
	public Entry core() {
		return Entry.instance;
	}

	public Date date(Object key) {
		Object result = argDeco().get(key);
		if (!(result instanceof Date) && result != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CoreObject.DATE_FORMAT);
			result = simpleDateFormat.parse(result.toString(), new ParsePosition(0));
		}
		if (result != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime((Date) result);
			cal.add(Calendar.HOUR, 7);
			result = cal.getTime();
		}
		return (Date) result;
	}

	@Transient
	public final HomeService getHomeService() {
		return new HomeService();
	}

	public NguoiDung fetchNhanVienSaving() {
		return fetchNguoiDung(true);
	}

	public void redirectLogin(HttpServletRequest req, HttpServletResponse res) {
		StringBuilder url = new StringBuilder();
		url.append(req.getScheme()) // http (https)
				.append("://") // ://
				.append(req.getServerName()); // localhost (domain name)
		if (req.getServerPort() != 80 && req.getServerPort() != 443) {
			url.append(":").append(req.getServerPort()); // app name
		}
		try {
			if (!live) {
				res.sendRedirect(url + req.getContextPath() + "/login");
			} else {
				res.sendRedirect(Utils.getLogoutCasUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void bootstrapNhanVien() {
		JPAQuery<NguoiDung> q = find(NguoiDung.class).where(QNguoiDung.nguoiDung.daXoa.isFalse())
				.where(QNguoiDung.nguoiDung.trangThai.eq(core().TT_AP_DUNG));
		if (q.fetchCount() == 0) {
			final NguoiDung nguoiDung = new NguoiDung("admin@greenglobal.vn", "Super Admin");
			nguoiDung.getQuyens().add("*");
			nguoiDung.updatePassword("tcx@123");
			nguoiDung.saveNotShowNotification();
		}
	}

	@Transient
	public Setting getSetting() {
		String key = BaseObject.class + "." + Setting.class;
		Setting result = (Setting) Executions.getCurrent().getAttribute(key);
		if (result == null || result.noId()) {
			result = find(Setting.class).fetchFirst();
			if (result == null) {
				result = new Setting();
				result.save();
			}
			Executions.getCurrent().setAttribute(key, result);
		}
		return result;
	}

	@Transient
	public boolean isNhanVienDaKhoa() {
		return !getNguoiDung().isCheckApDung();
	}

	@Transient
	public boolean isNhanVienDaKichHoat() {
		return !getNguoiDung().isCheckKichHoat();
	}

	@Command
	public void redirectQuanLyDuAn() {
		Executions.sendRedirect("/cp/quanlyduan");
	}

	@Command
	public void redirectQuanLyDoanVao() {
		Executions.sendRedirect("/cp/quanlydoanvao");
	}

	@Command
	public void redirectPage(@BindingParam("zul") String zul, @BindingParam("vmArgs") Object vmArgs,
			@BindingParam("vm") Object vm, @BindingParam("nhom") Object nhom) {
		Map<String, Object> args = new HashMap<>();
		args.put("vmArgs", vmArgs);
		args.put("vm", vm);
		args.put("nhom", nhom);
		Executions.createComponents(zul, null, args);
	}

	@Command
	public void redirectPageDoanVao(@BindingParam("zul") String zul, @BindingParam("vmArgs") Object vmArgs,
			@BindingParam("vm") Object vm, @BindingParam("nhom") Object nhom) {
		Map<String, Object> args = new HashMap<>();
		args.put("vmArgs", vmArgs);
		args.put("vm", vm);
		args.put("nhom", nhom);
		Executions.createComponents(zul, null, args);
	}

	@Command
	public void redirectPageAction(@BindingParam("zul") String zul, @BindingParam("vmArgs") Object vmArgs,
			@BindingParam("vm") Object vm, @BindingParam("nhom") Object nhom,
			@BindingParam("readOnly") boolean readOnly) {
		Map<String, Object> args = new HashMap<>();
		args.put("vmArgs", vmArgs);
		args.put("vm", vm);
		args.put("nhom", nhom);
		args.put("readOnly", readOnly);
		Executions.createComponents(zul, null, args);
	}

	@Command
	public void redirectPageActionGiaoViec(@BindingParam("zul") String zul, @BindingParam("vmArgs") Object vmArgs,
			@BindingParam("vm") Object vm, @BindingParam("nhom") Object nhom,
			@BindingParam("loaiCongViec") LoaiCongViec loaiCongViec, @BindingParam("readOnly") boolean readOnly,
			@BindingParam("title") String title, @BindingParam("attr") String attr) {
		Map<String, Object> args = new HashMap<>();
		args.put("vmArgs", vmArgs);
		args.put("vm", vm);
		args.put("nhom", nhom);
		args.put("readOnly", readOnly);
		args.put("title", title);
		args.put("loaiCongViec", loaiCongViec);
		args.put("attr", attr);
		Executions.createComponents(zul, null, args);
	}

	@SuppressWarnings("deprecation")
	protected CellStyle setBorderAndFont(final Workbook workbook, final int borderSize, final boolean isTitle,
			final int fontSize, final String fontColor, final String textAlign, final boolean boil) {
		final CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderTop((short) borderSize); // single line border
		cellStyle.setBorderBottom((short) borderSize); // single line border
		cellStyle.setBorderLeft((short) borderSize); // single line border
		cellStyle.setBorderRight((short) borderSize); // single line border

		if (textAlign.equals("RIGHT")) {
			cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		} else if (textAlign.equals("CENTER")) {
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		} else if (textAlign.equals("LEFT")) {
			cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		} else {
			// do nothing
		}

		if (boil) {
			final Font font = workbook.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			if (isTitle) {
				if (fontColor.equals("RED")) {
					font.setColor(Font.COLOR_RED);
				} else if (fontColor.equals("BLUE")) {
					font.setColor((short) 4);
				} else {
					// do no thing
				}
			}
			font.setFontHeightInPoints((short) fontSize);
			cellStyle.setFont(font);
		}
		return cellStyle;
	}

	public List<Long> listSubStringId(String text) {
		List<Long> list = new ArrayList<Long>();
		int start = text.indexOf("@");
		int close = text.indexOf("@", start + 1);
		while (start != -1 && close != -1) {
			list.add(Long.valueOf(text.substring(start + 1, close)));
			start = text.indexOf("@", close + 1);
			close = text.indexOf("@", start + 1);
		}
		return list;
	}

	public Date resetHourMinuteSecondMilli(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public String subStringThongBao(String text, int index) {
		int i = 0;
		for (String txt : text.split("@", 0)) {
			if (index == i) {
				return txt;
			}
			i++;
		}
		return null;
	}

	public String subStringThongBaoNoIndex(String text) {
		StringBuilder builder = new StringBuilder();
		for (String txt : text.split("@", 0)) {
			builder.append(txt);
		}
		return builder.toString();
	}

	public static final String KY_TU = "@";

	public String unAccent(String s) {
		String temp = Normalizer.normalize(s.toLowerCase(), Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll("đ", "d").replaceAll(" ", "")
				.replaceAll("[^a-zA-Z0-9 -]", "");
	}

	public void showNotification(String title, String content, String type) {
		switch (type) {
		case "success": {
			Clients.evalJavaScript("toastrSuccess('" + title + "', '" + content + "')");
			break;
		}
		case "info": {
			Clients.evalJavaScript("toastrInfo('" + title + "', '" + content + "')");
			break;
		}
		case "warning": {
			Clients.evalJavaScript("toastrWarning('" + title + "', '" + content + "')");
			break;
		}
		case "danger": {
			Clients.evalJavaScript("toastrError('" + title + "', '" + content + "')");
			break;
		}
		}

	}

	@Command
	public void invokeGG(@BindingParam("notify") Object vmArgs, @BindingParam("attr") String attrs,
			@BindingParam("detach") final Window wdn) {
		for (final String field : attrs.split("\\|")) {
			if (!field.isEmpty()) {
				BindUtils.postNotifyChange(null, null, vmArgs, field);
			}
		}
		if (wdn != null) {
			wdn.detach();
		}
	}

	public String getHomeUrl(String code) {
		String url = Executions.getCurrent().getHeader("host");
		if ("en".equals(code)) {
			return "http://" + url + "/web/en";
		}
		return "http://" + url;
	}

	@Transient
	public Date getBeginToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	@Transient
	public Date getBeginDateOf(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	@Transient
	public Date getEndToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	@Transient
	public Date getEndDateOf(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	@Transient
	public Date getToday() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	@Command
	public void notificate(String title, String content) {
		Map<String, Object> args = new HashMap<>();
		args.put("title", title);
		args.put("content", content);
		Executions.createComponents("/frontend/common/notification.zul", null, args);
	}

	@Command
	public void notificateError(String title, String content) {
		Map<String, Object> args = new HashMap<>();
		args.put("title", title);
		args.put("content", content);
		Executions.createComponents("/frontend/common/notification-error.zul", null, args);
	}

	@Override
	public String subString(String text, int size) {
		int l = text.length();
		int index = size > l ? l : size;
		while (index < l && ' ' != text.charAt(index)) {
			index++;
		}
		String tail = index < l ? " ..." : "";
		return text.substring(0, index) + tail;
	}

	public boolean checkQuyenSuaXoa(TrangThaiGiaoViec trangThai, Long id) {
		if (!TrangThaiGiaoViec.HOAN_THANH.equals(trangThai) && core().getNguoiDung().getId().equals(id)) {
			return true;
		}
		return false;
	}

	public boolean checkQuyenSuaXoaKeCongViec(Long id) {
		if (core().getNguoiDung().getId().equals(id)) {
			return true;
		}
		return false;
	}

	public String thoiHanConLai(Date thoiHan, TrangThaiGiaoViec trangThai) {
		if (TrangThaiGiaoViec.HOAN_THANH.equals(trangThai)) {
			return null;
		}
		if (thoiHan.compareTo(resetHourMinuteSecondMilli(new Date())) > 0) {
			StringBuilder txt = new StringBuilder();
			txt.append("<span class='color-txt-blue'>(Còn ");
			txt.append((thoiHan.getTime() - resetHourMinuteSecondMilli(new Date()).getTime()) / (24 * 60 * 60 * 1000));
			txt.append(" ngày)</span>");
			return txt.toString();
		} else if (thoiHan.compareTo(resetHourMinuteSecondMilli(new Date())) == 0) {
			return "<span class='color-txt-orange'>(Đã đến hạn)</span>";
		} else {
			return "<span class='color-txt-red'>(Đã quá hạn)</span>";
		}
	}

	public List<LoaiCongViec> getListLoaiCongViec() {
		List<LoaiCongViec> list = new ArrayList<LoaiCongViec>();
		list.add(null);
		list.add(LoaiCongViec.DU_AN);
		list.add(LoaiCongViec.DOAN_VAO);
		return list;
	}

}