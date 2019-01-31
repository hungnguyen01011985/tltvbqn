
package vn.toancauxanh.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Object;

import vn.toancauxanh.cms.service.HomeService;
import vn.toancauxanh.cms.service.LanguageService;
import vn.toancauxanh.cms.service.PhongBanService;
import vn.toancauxanh.model.VaiTro;

@SuppressWarnings({ "unused" })
@Configuration
@Controller
public class Entry extends BaseObject<Object> {
	static Entry instance;
	@Value("${trangthai.apdung}")
	public String TT_AP_DUNG = "";
	@Value("${trangthai.daxoa}")
	public String TT_DA_XOA = "";
	@Value("${trangthai.khongapdung}")
	public String TT_KHONG_AP_DUNG = "";

	@Value("${filestore.root}")
	public String FOLDER_ROOT = "";
	@Value("${filestore.files}")
	public String FOLDER_FILES = "";

	// No image url
	public String URL_M_NOIMAGE = "/assetsfe/images/lg_noimage.png";
	public String URL_S_NOIMAGE = "/assetsfe/images/sm_noimage.png";

	@Value("${action.xem}")
	public String XEM = ""; // duoc xem bat ky
	@Value("${action.list}")
	public String LIST = ""; // duoc vao trang list search url
	@Value("${action.sua}")
	public String SUA = ""; // duoc sua bat ky
	@Value("${action.xoa}")
	public String XOA = ""; // duoc xoa bat ky
	@Value("${action.them}")
	public String THEM = ""; // duoc them

	@Value("${url.phongban}")
	public String NGUOIDUNG = "";
	@Value("${url.nguoidung}")
	public String PHANQUYEN = "";
	@Value("${url.vaitro}")
	public String VAITRO = "";
	// uend
	public char CHAR_CACH = ':';
	public String CACH = CHAR_CACH + "";

	// Thêm các tùy chọn vai trò của chức năng tương ứng

	@Value("${url.phongban}" + ":" + "${action.list}")
	public String QUANLYPHONGBANLIST;
	@Value("${url.phongban}" + ":" + "${action.them}")
	public String QUANLYPHONGBANTHEM;
	@Value("${url.phongban}" + ":" + "${action.xem}")
	public String QUANLYPHONGBANXEM;
	@Value("${url.phongban}" + ":" + "${action.sua}")
	public String QUANLYPHONGBANSUA;
	@Value("${url.phongban}" + ":" + "${action.xoa}")
	public String QUANLYPHONGBANXOA;
	
	@Value("${url.nguoidung}" + ":" + "${action.list}")
	public String NGUOIDUNGLIST;
	@Value("${url.nguoidung}" + ":" + "${action.them}")
	public String NGUOIDUNGTHEM;
	@Value("${url.nguoidung}" + ":" + "${action.sua}")
	public String NGUOIDUNGSUA;
	@Value("${url.nguoidung}" + ":" + "${action.xoa}")
	public String NGUOIDUNGXOA;
	@Value("${url.nguoidung}" + ":" + "${action.xem}")
	public String NGUOIDUNGXEM;
	
	@Value("${url.phanquyen}" + ":" + "${action.xem}")
	public String PHANQUYENXEM;
	@Value("${url.phanquyen}" + ":" + "${action.them}")
	public String PHANQUYENTHEM;
	@Value("${url.phanquyen}" + ":" + "${action.sua}")
	public String PHANQUYENSUA;
	@Value("${url.phanquyen}" + ":" + "${action.xoa}")
	public String PHANQUYENXOA;
	@Value("${url.phanquyen}" + ":" + "${action.list}")
	public String PHANQUYENLIETKE;
	@Value("${url.phanquyen}" + ":" + "${action.tim}")
	public String PHANQUYENTIMKIEM;

	@Value("${url.vaitro}" + ":" + "${action.xem}")
	public String VAITROXEM = "";
	@Value("${url.vaitro}" + ":" + "${action.them}")
	public String VAITROTHEM = "";
	@Value("${url.vaitro}" + ":" + "${action.list}")
	public String VAITROLIST = "";
	@Value("${url.vaitro}" + ":" + "${action.xoa}")
	public String VAITROXOA = "";
	@Value("${url.vaitro}" + ":" + "${action.sua}")
	public String VAITROSUA = "";
	@Value("${url.vaitro}" + ":" + "${action.tim}")
	public String VAITROTIMKIEM;
	// aend
	public String[] getRESOURCES() { // Các title của vai trò
		return new String[] { NGUOIDUNG, VAITRO}; //
	}

	public String[] getACTIONS() {
		return new String[] { LIST, XEM, THEM, SUA, XOA};
	}

	static {
		File file = new File(Labels.getLabel("filestore.root") + File.separator + Labels.getLabel("filestore.folder"));
	}
	@Autowired
	public Environment env;

	@Autowired
	DataSource dataSource;

	public Entry() {
		super();
		setCore();
		instance = this;
	}

	@Bean
	public FilterRegistrationBean cacheFilter() {
		FilterRegistrationBean rs = new FilterRegistrationBean(new CacheFilter());
		rs.addUrlPatterns("*.css");
		rs.addUrlPatterns("*.js");
		rs.addUrlPatterns("*.wpd");
		rs.addUrlPatterns("*.wcs");
		rs.addUrlPatterns("*.jpg");
		rs.addUrlPatterns("*.jpeg");
		rs.addUrlPatterns("*.png");
		rs.addUrlPatterns("*.svg");
		rs.addUrlPatterns("*.gif");
		rs.addUrlPatterns("*.tff");
		return rs;
	}

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		if (getNhanVien(true, request, response) != null) {
			return "forward:/WEB-INF/zul/home.zul?resource=quanlygiaoviec&action=lietke&file=/WEB-INF/zul/quanlygiaoviec/list.zul";
		}
		return "forward:/WEB-INF/zul/login.zul";
	}

	@RequestMapping(value = "/cp")
	public String cp() {
		return "forward:/WEB-INF/zul/home.zul?resource=quanlygiaoviec&action=lietke&file=/WEB-INF/zul/quanlygiaoviec/list.zul";
	}

	@RequestMapping(value = "/cp/{path:.+$}")
	public String cp(@PathVariable String path) {
		return "forward:/WEB-INF/zul/home.zul?resource=" + path + "&action=lietke&file=/WEB-INF/zul/" + path
				+ "/list.zul";
	}
	
	@RequestMapping(value = "/cp/baocaothongke/{path:.+$}")
	public String add2(@PathVariable String path) {
		return "forward:/WEB-INF/zul/home.zul?resource=baocaothongke&action="+path+"&file=/WEB-INF/zul/baocaothongke/" + path
				+ ".zul";
	}
	
	@RequestMapping(value = "/cp/{rsc:.+$}/{path:.+$}")
	public String add2(@PathVariable String rsc, @PathVariable String path) {
		return "forward:/WEB-INF/zul/home.zul?resource=" + rsc + "&action=them&file=/WEB-INF/zul/" + rsc + "/" + path
				+ ".zul";
	}
	
	@RequestMapping(value = "/cp/{path:.+$}/edit/{id:\\d+}")
	public String edit2(@PathVariable String path, @PathVariable Long id) {
		return "forward:/WEB-INF/zul/home.zul?resource=" + path + "&action=sua&file=/WEB-INF/zul/" + path
				+ "/show-doan-vao.zul&id=" + id;
	}
	
	@RequestMapping(value = "/cp/{path:.+$}/detail/{id:\\d+}")
	public String detail(@PathVariable String path, @PathVariable Long id) {
		return "forward:/WEB-INF/zul/home.zul?resource=" + path + "&action=xem&file=/WEB-INF/zul/" + path
				+ "/show-chi-tiet-doan-vao.zul&id=" + id;
	}

	@RequestMapping(value = "/cp/quanlyduan/{id:\\d+}")
	public String giaidoan(@PathVariable Long id) {
		return "forward:/WEB-INF/zul/home.zul?resource=quanlyduan&action=lietke&file=/WEB-INF/zul/quanlyduan/add-giai-doan.zul&id="
				+ id;
	}
	
	@RequestMapping(value = "/cp/quanlyduan/chitiet/{id:\\d+}")
	public String xemChiTiet(@PathVariable Long id) {
		return "forward:/WEB-INF/zul/home.zul?resource=quanlyduan&action=lietke&file=/WEB-INF/zul/quanlyduan/view-giai-doan.zul&id="
				+ id;
	}
	
	//SSO
	@RequestMapping(value = "/sso-error")
	public String sso() {
		return "forward:/WEB-INF/zul/error-sso.zul";
	}
	
	@RequestMapping(value = "/dang-nhap-sso")
	public String loginSSO(HttpServletRequest request, HttpServletResponse response) {
		return "forward:/WEB-INF/zul/dang-nhap-sso.zul";
	}
	
	@RequestMapping(value = "/auth/logout")
	public void dangXuatBackend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			response.sendRedirect(request.getContextPath()+"/cas/login");
		} else {
			new NhanVienService().logoutNotRedirect(request, response);
		}	
	}

	public final PhongBanService getPhongBans() {
		return new PhongBanService();
	}

	public final NhanVienService getNhanViens() {
		return new NhanVienService();
	}

	public final VaiTroService getVaiTros() {
		return new VaiTroService();
	}

	public final Quyen getQuyen() {
		return getNhanVien().getTatCaQuyen();
	}

	public final HomeService getHomes() {
		return new HomeService();
	}

	public final LanguageService getLanguages() {
		return new LanguageService();
	}
	
	public boolean checkVaiTro(String vaiTro) {
		if (vaiTro == null || vaiTro.isEmpty()) {
			return false;
		}
		boolean rs = false;
		for (VaiTro vt : getNhanVien().getVaiTros()) {
			if (vaiTro.equals(vt.getAlias())) {
				rs = true;
				break;
			}
		}
		return rs;// || getQuyen().get(vaiTro);
	}

	@Configuration
	@EnableWebMvc
	public static class MvcConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/files/**").addResourceLocations("/home/bxtdtdndata/bxtdtdnfiles/");
			registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
			registry.addResourceHandler("/backend/**").addResourceLocations("/backend/");
			registry.addResourceHandler("/img/**").addResourceLocations("/img/");
			registry.addResourceHandler("/login/**").addResourceLocations("/login/");
		}

		@Override
		public void configureViewResolvers(final ViewResolverRegistry registry) {
			registry.jsp("/WEB-INF/", "*");
		}

		@ExceptionHandler(ResourceNotFoundException.class)
		@ResponseStatus(HttpStatus.NOT_FOUND)
		public String handleResourceNotFoundException() {
			return "forward:/WEB-INF/zul/notfound.zul";
		}
	}

}