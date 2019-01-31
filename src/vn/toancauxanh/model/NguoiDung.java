package vn.toancauxanh.model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.google.common.base.Strings;
import com.querydsl.jpa.impl.JPAQuery;

//import vn.toancauxanh.gg.model.DonVi;
import vn.toancauxanh.service.Quyen;

@Entity
@Table(name = "nguoidung")
public class NguoiDung extends Model<NguoiDung> {
	private String tenHienThi = "";
	private String taiKhoan="";
	private String email = "";
	private String loaiTaiKhoan = "";
	private String loaiXacThuc;
	private String matKhau = "";
	private String salkey = "";
	private boolean block;
	private boolean active;
	private Set<String> quyens = new HashSet<>();
	private Set<String> tatCaQuyens = new HashSet<>();
	private Set<VaiTro> vaiTros = new HashSet<>();
	private VaiTro vaiTro;
	private Quyen quyen = new Quyen(new SimpleAccountRealm() {
		@Override
		protected AuthorizationInfo getAuthorizationInfo(final PrincipalCollection arg0) {
			final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.setStringPermissions(getTatCaQuyens());
			return info;
		}
	});
	
	public NguoiDung() {
		super();
	}

	public String getTenHienThi() {
		return tenHienThi;
	}

	public void setTenHienThi(String tenHienThi) {
		this.tenHienThi = tenHienThi;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getLoaiTaiKhoan() {
		return loaiTaiKhoan;
	}

	public void setLoaiTaiKhoan(String loaiTaiKhoan) {
		this.loaiTaiKhoan = loaiTaiKhoan;
	}

	public String getLoaiXacThuc() {
		return loaiXacThuc;
	}

	public void setLoaiXacThuc(String loaiXacThuc) {
		this.loaiXacThuc = loaiXacThuc;
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getSalkey() {
		return salkey;
	}

	public void setSalkey(String salkey) {
		this.salkey = salkey;
	}

	@ManyToOne
	public VaiTro getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(VaiTro vaiTro) {
		this.vaiTro = vaiTro;
	}


	@ElementCollection(fetch = FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@CollectionTable(name = "nguoidung_quyens", joinColumns = { @JoinColumn(name = "nguoiDung_id") })
	public Set<String> getQuyens() {
		return quyens;
	}

	@Transient
	public Set<String> getTatCaQuyens() {
		if (tatCaQuyens.isEmpty()) {
			tatCaQuyens.addAll(quyens);
			for (VaiTro vaiTro : vaiTros) {
				if (!vaiTro.getAlias().isEmpty()) {
					// tatCaQuyens.add(vaiTro.getAlias());
				}
				tatCaQuyens.addAll(vaiTro.getQuyens());
			}
			if (Labels.getLabel("email.superuser").equals(email)) {
				tatCaQuyens.add("*");
			}
		}
		return tatCaQuyens;
	}

	public void setQuyens(final Set<String> dsChoPhep) {
		quyens = dsChoPhep;
	}

	@Transient
	public String getVaiTroText() {
		String result = "";
		for (VaiTro vt : getVaiTros()) {
			result += (result.isEmpty() ? "" : ", ") + vt.getTenVaiTro();
		}
		return result;
	}

	@Transient
	public String getFirstAlias() {
		String result = "";
		for (VaiTro vt : getVaiTros()) {
			result = vt.getAlias();
			break;
		}
		return result;
	}


	public NguoiDung(String email, String taiKhoan) {
		super();
		this.taiKhoan = taiKhoan;
		this.email = email;
	}

	@Override
	public void doSave() {
		super.doSave();
	}

	public String getEmail() {
		return email;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public boolean changePass;

	@Transient
	public boolean isChangePass() {
		return changePass;
	}

	public void setChangePass(boolean changePass) {
		this.changePass = changePass;
	}

	public String matKhauEdit;

	@Transient
	public String getMatKhauEdit() {
		return matKhauEdit;
	}

	public void setMatKhauEdit(String matKhauEdit) {
		this.matKhauEdit = matKhauEdit;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "nguoidung_vaitro", joinColumns = { @JoinColumn(name = "nguoidung_id") }, inverseJoinColumns = {
			@JoinColumn(name = "vaitros_id") })
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public Set<VaiTro> getVaiTros() {
		return vaiTros;
	}

	public void setEmail(final String _email) {
		email = Strings.nullToEmpty(_email);
	}

	public void setMatKhau(final String _matKhau) {
		matKhau = Strings.nullToEmpty(_matKhau);
	}

	public void setVaiTros(final Set<VaiTro> vaiTros1) {
		vaiTros = vaiTros1;
	}

	@Transient
	public Quyen getTatCaQuyen() {
		return quyen;
	}

	@Transient // Khai báo k khởi tạo xuống db
	public AbstractValidator getValidator() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				// if (getVaiTros().size() == 0) {
				// addInvalidMessage(ctx, "lblErrVaiTros", "Bạn phải chọn vai
				// trò cho người dùng");
				// }
			}
		};
	}

	@Transient
	public AbstractValidator getValidator(boolean isBackend) {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				if (isBackend && getVaiTros() != null && getVaiTros().size() == 0) {
					addInvalidMessage(ctx, "lblErrVaiTros", "Bạn phải chọn vai trò cho người dùng.");
				}
			}
		};
	}

	@Transient
	public AbstractValidator getValidatePassword() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				final Object mKhau = ctx.getValidatorArg("password");
				if (mKhau == null) {
				} else {
					Object pass = ctx.getProperty().getValue();
					if (pass == null) {
						pass = "";
					}
					if (mKhau.equals(pass)) {
					} else {
						addInvalidMessage(ctx, "Xác nhận mật khẩu không trùng khớp!");
					}
				}
			}
		};
	}

	@Command
	public void khoaThanhVien(@BindingParam("vm") final Object vm) {
		if ("admin@greenglobal.vn".equals(getEmail())) {
			showNotification("Không thể khóa SuperUser", "", "warning");
		} else {
			Messagebox.show("Bạn muốn khóa nhân viên này?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
					Messagebox.QUESTION, new EventListener<Event>() {
						@Override
						public void onEvent(final Event event) {
							if (Messagebox.ON_OK.equals(event.getName())) {
								setCheckApDung(false);
								save();
								BindUtils.postNotifyChange(null, null, vm, "targetQueryNhanVien");
							}
						}
					});
		}
	}

	@Command
	public void moKhoaThanhVien(@BindingParam("vm") final Object vm) {
		Messagebox.show("Bạn muốn mở khóa nhân viên này?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) {
						if (Messagebox.ON_OK.equals(event.getName())) {
							setCheckApDung(true);
							save();
							BindUtils.postNotifyChange(null, null, vm, "targetQueryNhanVien");
						}
					}
				});
	}

	private boolean checkKichHoat;

	public boolean isCheckKichHoat() {
		return checkKichHoat;
	}

	public void setCheckKichHoat(boolean checkKichHoat) {
		this.checkKichHoat = checkKichHoat;
	}

	@Command
	public void toggleLock(@BindingParam("list") final Object obj) {
		String dialogText = "";
		if (!checkKichHoat) {
			dialogText = "Bạn muốn ngưng kích hoạt người dùng đã chọn?";
		} else {
			dialogText = "Bạn muốn kích hoạt người dùng đã chọn?";
		}
		Messagebox.show(dialogText, "Xác nhận", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
				new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) {
						if (Messagebox.ON_OK.equals(event.getName())) {
							if (checkKichHoat) {
								setCheckKichHoat(false);
							} else {
								setCheckKichHoat(true);
							}
							save();
							BindUtils.postNotifyChange(null, null, obj, "targetQueryNhanVien");
						}
					}
				});
	}

	@Command
	public void deleteNhanVienInListVaiTro(@BindingParam("vaitro") final VaiTro vt,
			@BindingParam("nhanvien") final NguoiDung nv) {
		Messagebox.show("Bạn có chắc chắn muốn xóa vai trò " + vt.getTenVaiTro() + " của nhân viên " + nv.getTenHienThi(),
				"Xác nhận", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) {
						if (Messagebox.ON_OK.equals(event.getName())) {
							vaiTros.remove(vt);

							save();
							BindUtils.postNotifyChange(null, null, vt, "listNhanVien");
						}
					}
				});
	}

	public String getCookieToken(long expire) {
		String token = getId() + ":" + expire + ":";
		return Base64.encodeBase64String(token.concat(DigestUtils.md5Hex(token + matKhau + ":" + salkey)).getBytes());
	}

	public void updatePassword(String pass) {
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		String salkey = getSalkey();

		if (salkey == null || salkey.equals("")) {
			salkey = encryptor.encryptPassword((new Date()).toString());
		}

		String passNoHash = pass + salkey;
		String passHash = encryptor.encryptPassword(passNoHash);
		setSalkey(salkey);
		setMatKhau(passHash);
	}

	@Transient
	public List<Object> getListThongBao() {
		List<Object> list = new ArrayList<Object>();
		return list;
	}

	@Transient
	public AbstractValidator getValidatorEmail() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = (String) ctx.getProperty().getValue();
				String param = value.trim().replaceAll("\\s+", "");
				if (param == null || "".equals(param) || param.isEmpty()) {
					addInvalidMessage(ctx, "error", "Không được để trống trường này");
				} else if (!value.trim().matches(".+@.+\\.[a-z]+")) {
					addInvalidMessage(ctx, "error", "Email không đúng định dạng");
				} else {
					JPAQuery<NguoiDung> q = find(NguoiDung.class).where(QNguoiDung.nguoiDung.email.eq(value))
							.where(QNguoiDung.nguoiDung.trangThai.ne(core().TT_DA_XOA));
					if (!NguoiDung.this.noId()) {
						q.where(QNguoiDung.nguoiDung.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "error", "Email đã được sử dụng");
					}
				}
			}
		};
	}

	public boolean change = false;
	public boolean editable = false;

	@Transient
	public boolean isChange() {
		return change;
	}

	public void setChange(boolean change) {
		this.change = change;
	}

	@Transient
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Command
	public void ChangePassword() {
		setChange(isChange() ? false : true);
		BindUtils.postNotifyChange(null, null, this, "change");
	}

	@Command
	public void editableStatus() {
		setEditable(true);
		setChange(true);

		BindUtils.postNotifyChange(null, null, this, "editable");
		BindUtils.postNotifyChange(null, null, this, "change");
	}

	@Command
	public void lockNhanVien(@BindingParam("vm") Object vm, @BindingParam("object") NguoiDung object) {
		Messagebox.show("Bạn có muốn khóa nhân viên này ?", "Khóa nhân viên", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						if (Messagebox.ON_OK.equals(event.getName())) {
							setCheckApDung(false);
							save();
							BindUtils.postNotifyChange(null, null, vm, "targetQueryNhanVien");
						}

					}

				});
	}

	@Command
	public void openLockNhanVien(@BindingParam("vm") Object vm) {
		Messagebox.show("Bạn có muốn mở khóa nhân viên này ?", "Mở khóa nhân viên", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						if (Messagebox.ON_OK.equals(event.getName())) {
							setCheckApDung(true);
							save();
							BindUtils.postNotifyChange(null, null, vm, "targetQueryNhanVien");
						}

					}

				});
	}

	@Transient
	public AbstractValidator getValidateSoDienThoai() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = (String) ctx.getProperty().getValue();
				if (!value.isEmpty() && !value.trim()
						.matches("^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$")) {
					addInvalidMessage(ctx, "error", "Số điện thoại không đúng định dạng.");
				}
			}
		};
	}

	@Transient
	public AbstractValidator getValidateMatKhauCu() {
		NguoiDung nhanVien = this;
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = (String) ctx.getProperty().getValue();
				if (isChangePass() == true) {
					BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
					String passNoHash = value + nhanVien.getSalkey();
					if (!encryptor.checkPassword(passNoHash, nhanVien.getMatKhau())) {
						addInvalidMessage(ctx, "error", "Mật khẩu cũ không chính xác");
					}
				}
			}
		};
	}

	@Transient
	public AbstractValidator getValidateVaiTro() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				Object value = (Object) ctx.getProperty().getValue();
				if (value == null) {
					addInvalidMessage(ctx, "error", "Bạn chưa chọn vai trò người dùng");
				}
			}
		};
	}

	@Command
	public void cancelNoVm(@BindingParam("wdn") Window wdn) {
		wdn.detach();
	}

	@Transient
	public AbstractValidator getValidateMatKhauMoiEdit() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = ((String) ctx.getProperty().getValue()).trim().replaceAll("\\s+", "");
				if (isChangePass()) {
					if ("".equals(value) && value.isEmpty()) {
						addInvalidMessage(ctx, "Mật khẩu mới không để trống");
					}
					if (value.length() < 6) {
						addInvalidMessage(ctx, "Mật khẩu phải có tối thiểu 6 kí tự");
					}
				}
			}
		};
	}

	@Transient
	public AbstractValidator getValidateTrungKhop() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String pass = (String) ctx.getBindContext().getValidatorArg("pass");
				String value = (String) ctx.getProperty().getValue();
				String param = value.trim().replaceAll("\\s+", "");
				if ("".equals(param) && param.isEmpty()) {
					addInvalidMessage(ctx, "Xác nhận mật khẩu không để trống");
				} else {
					if (!value.equals(pass)) {
						addInvalidMessage(ctx, "Xác nhận mật khẩu không trùng khớp");
					}
				}
			}
		};
	}

	@Transient
	public AbstractValidator getValidateMatKhauMoi() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String pass = (String) ctx.getBindContext().getValidatorArg("pass");
				String value = (String) ctx.getProperty().getValue();
				String param = value.trim().replaceAll("\\s+", "");
				if (isChangePass()) {
					if ("".equals(param) && param.isEmpty()) {
						addInvalidMessage(ctx, "Xác nhận mật khẩu không để trống");
					} else {
						if (!value.equals(pass)) {
							addInvalidMessage(ctx, "Xác nhận mật khẩu không trùng khớp");
						}
					}
				}
			}
		};
	}

	// đã sửa đây
	public void updateTokenNguoiDung() {
		String cookieToken = this
				.getCookieToken(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(6, TimeUnit.HOURS));
		Session zkSession = Sessions.getCurrent();
		zkSession.setAttribute("email", cookieToken);
		HttpServletResponse res = (HttpServletResponse) Executions.getCurrent().getNativeResponse();
		Cookie cookie = new Cookie("email", cookieToken);
		cookie.setPath("/");
		cookie.setMaxAge(1000000000);
		res.addCookie(cookie);
	}

	@Command
	public void close(@BindingParam("wdn") Window wdn, @BindingParam("vm") NguoiDung nhanVien)
			throws FileNotFoundException, IOException {
		JPAQuery<NguoiDung> q = find(NguoiDung.class).where(QNguoiDung.nguoiDung.eq(nhanVien));
		nhanVien = q.fetchOne();
		BindUtils.postNotifyChange(null, null, nhanVien, "*");
		wdn.detach();
	}

	@Command
	public void redirectEditUser(@BindingParam("zul") String zul, @BindingParam("vm") NguoiDung vm) {
		NguoiDung nhanVienEdit = find(NguoiDung.class).where(QNguoiDung.nguoiDung.eq(vm)).fetchFirst();
		Map<String, Object> args = new HashMap<>();
		args.put("vm", nhanVienEdit);
		args.put("vm2", vm);
		Executions.createComponents(zul, null, args);

	}

	@Transient
	public List<String> getListNhom() {
		List<String> listNhom = new ArrayList<>();
		for (VaiTro vaiTro : getVaiTros()) {
			listNhom.add(vaiTro.getAlias());
		}
		if (listNhom.isEmpty()) {
			listNhom.add("*");
		}
		return listNhom;
	}
}
