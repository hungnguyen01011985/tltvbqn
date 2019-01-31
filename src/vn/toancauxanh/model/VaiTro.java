package vn.toancauxanh.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.Window;

import com.google.common.base.Strings;
import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.LoaiVaiTro;
import vn.toancauxanh.service.Quyen;

@Entity
@Table(name = "vaitro", indexes = { @Index(columnList = "alias"), @Index(columnList = "tenVaiTro") })
public class VaiTro extends Model<VaiTro> {
	public static transient final Logger LOG = LogManager.getLogger(VaiTro.class.getName());

	public static final String QUANTRIHETHONG = "quantrihethong";
	public static final String CHUYENVIEN = "chuyenvien";
	public static final String TRUONGPHONG = "truongphong";
	public static final String LANHDAO = "lanhdao";

	public static final String[] VAITRO_DEFAULTS = { QUANTRIHETHONG, CHUYENVIEN, LANHDAO, TRUONGPHONG };

	private Set<String> quyens = new HashSet<>();
	private Set<String> quyenEdits = quyens;
	private String tenVaiTro = "";
	private String alias = "";
	private int soThuTu;
	private LoaiVaiTro loaiVaiTro;

	public VaiTro() {
		super();
	}

	public VaiTro(String ten, String quyen, LoaiVaiTro loaiVaiTro) {
		super();
		tenVaiTro = ten;
		setAlias(quyen.trim());
		this.loaiVaiTro = loaiVaiTro;
	}

	public int getSoThuTu() {
		return soThuTu;
	}

	public void setSoThuTu(int soThuTu) {
		this.soThuTu = soThuTu;
	}

	@Transient
	public List<NguoiDung> getListNhanVien() {
		JPAQuery<NguoiDung> q = find(NguoiDung.class)
				.where(QNguoiDung.nguoiDung.trangThai.ne(core().TT_DA_XOA))
				.where(QNguoiDung.nguoiDung.vaiTros.contains(this));
		return q.fetch();
	}
	
	@Transient
	public List<LoaiVaiTro> getListLoaiVaiTro() {
		List<LoaiVaiTro> list = new ArrayList<LoaiVaiTro>();
		list.add(LoaiVaiTro.VAI_TRO_QUAN_TRI);
		list.add(LoaiVaiTro.VAI_TRO_TRUONG_PHONG);
		list.add(LoaiVaiTro.VAI_TRO_CHUYEN_VIEN);
		list.add(LoaiVaiTro.VAI_TRO_LANH_DAO);
		return list;
	}

	@Enumerated(EnumType.STRING)
	public LoaiVaiTro getLoaiVaiTro() {
		return loaiVaiTro;
	}

	public void setLoaiVaiTro(LoaiVaiTro loaiVaiTro) {
		this.loaiVaiTro = loaiVaiTro;
	}

	Set<TreeNode<String[]>> selectedItems = new HashSet<>();
	
	@Transient
	public List<String> getListParentVaiTro() {
		List<String> list = new ArrayList<String>();
		for(String parent : core().getRESOURCES()) {
			list.add(parent);
		}
		return list;
	}
	
	@Transient
	public String[] getListParentVaiTro1() {
		Set<String> quyens = new HashSet<>();
		quyens.addAll(getQuyens());
		if (!quyens.isEmpty()) {
			selectItemVaiTro.addAll(quyens);
		}
		return core().getRESOURCES();
	}
	
	@Transient
	public String getLabelParentVaiTro(String resource) {
		return Labels.getLabel("url." + resource + ".mota").toLowerCase();
	}
	
	@Transient
	public String getLabelChildrenVaiTro(String action) {
		return Labels.getLabel("action." + action + ".mota");
	}
	
	@Transient
	public String getLabelAction(String action, String parent) {
		return Labels.getLabel("action." + action.replaceFirst(parent + (core().CHAR_CACH), "") + ".mota");
	}
	
	
	@Transient
	public List<String> getListChildrenVaiTro(String resource) {
		List<String> list = new ArrayList<String>();
		list.add(null);
		final Set<String> allQuyens = new HashSet<>();
		allQuyens.addAll(getQuyenAllMacDinhs());
		for (String action : core().getACTIONS()) {
			String quyen = resource + Quyen.CACH + action;
			if (allQuyens.contains(quyen)) {
				list.add(quyen);
			}
		}
		return list;
	}
	
	Set<String> selectItemVaiTro = new HashSet<String>();
	
	@Transient
	public Set<String> getSelectItemVaiTro() {
		return selectItemVaiTro;
	}

	public void setSelectItemVaiTro(Set<String> selectItemVaiTro) {
		this.selectItemVaiTro = selectItemVaiTro;
	}
	
	@Command
	public void doChecked(@BindingParam("children") String children, @BindingParam("parent") String parent) {
		String paramAll = "all".concat(parent);
		if (children == null) {
			if (!selectItemVaiTro.contains(paramAll)) {
				selectItemVaiTro.add(paramAll);
				selectItemVaiTro.addAll(getQuyenByVaiTro(parent));
			} else {
				selectItemVaiTro.remove(paramAll);
				selectItemVaiTro.removeAll(getQuyenByVaiTro(parent));
			}
		} else if (selectItemVaiTro.contains(children)) {
			selectItemVaiTro.remove(children);
			selectItemVaiTro.remove(paramAll);
		} else {
			selectItemVaiTro.add(children);
			if (selectItemVaiTro.containsAll(getQuyenByVaiTro(parent))) {
				selectItemVaiTro.add(paramAll);
			}
		}
		BindUtils.postNotifyChange(null, null, this, "selectItemVaiTro");
	}
	
	@Transient
	@NotifyChange({ "selectedItems", "model", "*" })
	public DefaultTreeModel<String[]> getModel() {
		getQuyens();
		selectedItems.clear();

		final HashSet<TreeNode<String[]>> openItems_ = new HashSet<>();

		final TreeNode<String[]> rootNode = new DefaultTreeNode<>(new String[] {},
				new ArrayList<DefaultTreeNode<String[]>>());

		final DefaultTreeModel<String[]> model = new DefaultTreeModel<>(rootNode, true);

		model.setMultiple(true);
		final Set<String> allQuyens = new HashSet<>();

		final long q = find(VaiTro.class).fetchCount();

		if (q == 0) {
			for (String vaiTro : VAITRO_DEFAULTS) {
				allQuyens.addAll(getQuyenMacDinhs(vaiTro));
			}
		} else {
			allQuyens.addAll(getQuyenAllMacDinhs());
		}

		for (String resource : core().getRESOURCES()) {
			DefaultTreeNode<String[]> parentNode = new DefaultTreeNode<>(
					new String[] { Labels.getLabel("url." + resource + ".mota"), resource },
					new ArrayList<DefaultTreeNode<String[]>>());
			if (quyens.contains(resource)) {
				selectedItems.add(parentNode);
				openItems_.add(parentNode);
				model.setOpenObjects(openItems_);
			}
			for (String action : core().getACTIONS()) {
				String quyen = resource + Quyen.CACH + action;
				if (allQuyens.contains(quyen)) {
					DefaultTreeNode<String[]> childNode = new DefaultTreeNode<>(
							new String[] { Labels.getLabel("action." + action + ".mota"), quyen },
							new ArrayList<DefaultTreeNode<String[]>>());
					if (quyens.contains(quyen)) {
						selectedItems.add(childNode);
						openItems_.add(childNode);
						openItems_.add(parentNode);
					}
					parentNode.add(childNode);
				}
			}
			rootNode.add(parentNode);
		}
		quyenEdits = new HashSet<>(quyens);
		model.setOpenObjects(openItems_);
		return model;
	}

	public String getAlias() {
		return alias;
	}
	
	@Transient
	public Set<String> getQuyenByVaiTro(String parent) {
		Set<String> list = new HashSet<String>();
		
		if ("vaitro".equals(parent)) {
			list.add(core().VAITROLIST);
			list.add(core().VAITROXEM);
			list.add(core().VAITROTHEM);
			list.add(core().VAITROSUA);
			list.add(core().VAITROXOA);
			list.add(core().VAITROTIMKIEM);
		}
		return list;
	}

	@Transient
	public Set<String> getQuyenAllMacDinhs() {
		Set<String> quyens1 = new HashSet<>();
		// Thêm quyền vào danh sách vai trò các tittle
		
		quyens1.add(core().VAITROLIST);
		quyens1.add(core().VAITROXEM);
		quyens1.add(core().VAITROTHEM);
		quyens1.add(core().VAITROSUA);
		quyens1.add(core().VAITROXOA);
		quyens1.add(core().VAITROTIMKIEM);
		return quyens1;
	}
	
	@Transient
	public Set<String> getQuyenMacDinhs(String vaiTro) {
		Set<String> quyens1 = new HashSet<>();
		if (!vaiTro.isEmpty()) {
			if (QUANTRIHETHONG.equals(vaiTro)) {

				// Thêm quyền vào danh sách vai trò các tittle
				
				quyens1.add("all".concat(core().VAITRO));
				quyens1.add(core().VAITROLIST);
				quyens1.add(core().VAITROXEM);
				quyens1.add(core().VAITROTHEM);
				quyens1.add(core().VAITROSUA);
				quyens1.add(core().VAITROXOA);
				quyens1.add(core().VAITROTIMKIEM);

				quyens1.add("all".concat(core().NGUOIDUNG));
				quyens1.add(core().NGUOIDUNGLIST);
				quyens1.add(core().NGUOIDUNGXEM);
				quyens1.add(core().NGUOIDUNGTHEM);
				quyens1.add(core().NGUOIDUNGSUA);
				quyens1.add(core().NGUOIDUNGXOA);

			} else if (CHUYENVIEN.equals(vaiTro)) {

				// Thêm quyền vào danh sách vai trò các tittle

				quyens1.add(core().VAITROXEM);
				quyens1.add(core().VAITROTHEM);
				quyens1.add(core().VAITROSUA);
				quyens1.add(core().VAITROXOA);
				quyens1.add(core().VAITROTIMKIEM);
				
				quyens1.add("all".concat(core().NGUOIDUNG));
				quyens1.add(core().NGUOIDUNGLIST);
				quyens1.add(core().NGUOIDUNGXEM);
				quyens1.add(core().NGUOIDUNGTHEM);
				quyens1.add(core().NGUOIDUNGSUA);
				quyens1.add(core().NGUOIDUNGXOA);
			} else if (LANHDAO.equals(vaiTro)) {
				// Thêm quyền vào danh sách vai trò các tittle
				

				quyens1.add("all".concat(core().VAITRO));
				quyens1.add(core().VAITROXEM);
				quyens1.add(core().VAITROTHEM);
				quyens1.add(core().VAITROSUA);
				quyens1.add(core().VAITROXOA);
				quyens1.add(core().VAITROTIMKIEM);

				quyens1.add("all".concat(core().NGUOIDUNG));
				quyens1.add(core().NGUOIDUNGLIST);
				quyens1.add(core().NGUOIDUNGXEM);
				quyens1.add(core().NGUOIDUNGTHEM);
				quyens1.add(core().NGUOIDUNGSUA);
				quyens1.add(core().NGUOIDUNGXOA);
			} else if (TRUONGPHONG.equals(vaiTro)) {
				// Thêm quyền vào danh sách vai trò các tittle
				quyens1.add("all".concat(core().VAITRO));
				quyens1.add(core().VAITROXEM);
				quyens1.add(core().VAITROTHEM);
				quyens1.add(core().VAITROSUA);
				quyens1.add(core().VAITROXOA);
				quyens1.add(core().VAITROTIMKIEM);

				quyens1.add("all".concat(core().NGUOIDUNG));
				quyens1.add(core().NGUOIDUNGLIST);
				quyens1.add(core().NGUOIDUNGXEM);
				quyens1.add(core().NGUOIDUNGTHEM);
				quyens1.add(core().NGUOIDUNGSUA);
				quyens1.add(core().NGUOIDUNGXOA);
			}

		}
		return quyens1;
	}

	@Transient
	public Set<String> getQuyenMacDinhs() {
		return getQuyenMacDinhs(getAlias());
	}
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@CollectionTable(name = "vaitro_quyens", joinColumns = {@JoinColumn(name = "vaitro_id")})
	public Set<String> getQuyens() {
		if (quyens.isEmpty()) {
			quyens.addAll(getQuyenMacDinhs());
		}
		return quyens;
	}

	public String getTenVaiTro() {
		return tenVaiTro;
	}

	public void setAlias(String alias1) {
		this.alias = Strings.nullToEmpty(alias1);
	}

	public void setQuyens(final Set<String> dsChoPhep) {
		quyens = dsChoPhep;
	}

	public void setTenVaiTro(final String _tenVaiTro) {
		tenVaiTro = Strings.nullToEmpty(_tenVaiTro);
	}

	@Override
	public String toString() {
		return super.toString() + " " + tenVaiTro;
	}

	@Transient
	public Set<TreeNode<String[]>> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(Set<TreeNode<String[]>> selectedItems_) {
		Set<TreeNode<String[]>> selectedItemsTmp = new HashSet<>();
		selectedItemsTmp.addAll(selectedItems);
		for (final TreeNode<String[]> node : selectedItems) {
			if (!selectedItems_.contains(node)) {
				quyenEdits.remove(node.getData()[1]);
				selectedItemsTmp.remove(node);

				// remove parent
				TreeNode<String[]> pNode = node.getParent();
				if (pNode != null && selectedItems_.contains(pNode)) {
					quyenEdits.remove(pNode.getData()[1]);
					selectedItemsTmp.remove(pNode);
				}
				// remove all child
				if (node.getChildCount() > 0) {
					for (TreeNode<String[]> n : node.getChildren()) {
						quyenEdits.remove(n.getData()[1]);
						selectedItemsTmp.remove(n);
					}
				}
			}
		}
		for (final TreeNode<String[]> node : selectedItems_) {
			if (!selectedItems.contains(node)) {
				quyenEdits.add(node.getData()[1]);
				selectedItemsTmp.add(node);
				if (node.getChildCount() > 0) {
					for (TreeNode<String[]> n : node.getChildren()) {
						quyenEdits.add(n.getData()[1]);
						selectedItemsTmp.add(n);
					}
				}
			}
		}
		selectedItems = selectedItemsTmp;
		BindUtils.postNotifyChange(null, null, this, "quyenEdits");
		BindUtils.postNotifyChange(null, null, this, "selectedItems");
	}

	private boolean checkApDung;

	@Transient
	public boolean isCheckApDung() {
		checkApDung = false;
		if (core().TT_AP_DUNG.equals(getTrangThai())) {
			checkApDung = true;
		}
		return checkApDung;
	}

	public void setCheckApDung(boolean _isCheckApDung) {
		if (_isCheckApDung) {
			setTrangThai(core().TT_AP_DUNG);
		} else {
			setTrangThai(core().TT_KHONG_AP_DUNG);
		}
		this.checkApDung = _isCheckApDung;
	}

	private boolean checkKichHoat;

	public boolean isCheckKichHoat() {
		return checkKichHoat;
	}

	public void setCheckKichHoat(boolean checkKichHoat) {
		this.checkKichHoat = checkKichHoat;
	}
	@Command
	public void saveVaiTro(@BindingParam("list") final Object listObject,
			@BindingParam("attr") final String attr,
			@BindingParam("wdn") final Window wdn) {
		if (selectItemVaiTro.isEmpty()) {
			showNotification("", "Bạn chưa chọn quyền nào cho vai trò này", "danger");
			return;
		}
		setTenVaiTro(getTenVaiTro().trim().replaceAll("\\s+", " "));
		setQuyens(selectItemVaiTro);
		save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, listObject, "vaiTroQuery");
	}
	@Override
	public void save() {
		if (noId()) {
			showNotification("Đã lưu thành công!", "", "success");
		} else {
			showNotification("Đã cập nhật thành công!", "", "success");
		}
		doSave();
	}
	@Transient
	public boolean isMacDinh() {
		return Arrays.asList(VAITRO_DEFAULTS).contains(this.getAlias());
	}
	
	@Transient
	public AbstractValidator getValidateTenVaiTro() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				String tenVaiTro = (String) ctx.getProperty().getValue();
				String param = tenVaiTro.trim().replaceAll("\\s+", "");
				if (!"".equals(param) && param != null && !param.isEmpty()) {
					JPAQuery<VaiTro> q = find(VaiTro.class).where(QVaiTro.vaiTro.tenVaiTro.eq(tenVaiTro));
					if (!VaiTro.this.noId()) {
						q.where(QVaiTro.vaiTro.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Đã tồn tại vai trò này");
					}
				} else {
					addInvalidMessage(ctx, "Không được để trống trường này");
				}
			}
		};
	}
}
