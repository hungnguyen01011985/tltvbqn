package vn.toancauxanh.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNguoiDung is a Querydsl query type for NguoiDung
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNguoiDung extends EntityPathBase<NguoiDung> {

    private static final long serialVersionUID = 805708009L;

    private static final PathInits INITS = new PathInits("*", "nguoiSua.*.*.*.*", "nguoiTao.*.*.*.*");

    public static final QNguoiDung nguoiDung = new QNguoiDung("nguoiDung");

    public final QModel _super;

    public final BooleanPath active = createBoolean("active");

    public final BooleanPath block = createBoolean("block");

    public final BooleanPath checkKichHoat = createBoolean("checkKichHoat");

    //inherited
    public final BooleanPath daXoa;

    public final StringPath email = createString("email");

    //inherited
    public final NumberPath<Long> id;

    public final StringPath loaiTaiKhoan = createString("loaiTaiKhoan");

    public final StringPath loaiXacThuc = createString("loaiXacThuc");

    public final StringPath matKhau = createString("matKhau");

    //inherited
    public final DateTimePath<java.util.Date> ngaySua;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao;

    // inherited
    public final QNguoiDung nguoiSua;

    // inherited
    public final QNguoiDung nguoiTao;

    public final SetPath<String, StringPath> quyens = this.<String, StringPath>createSet("quyens", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath salkey = createString("salkey");

    public final StringPath taiKhoan = createString("taiKhoan");

    public final StringPath tenHienThi = createString("tenHienThi");

    //inherited
    public final StringPath trangThai;

    public final QVaiTro vaiTro;

    public final SetPath<VaiTro, QVaiTro> vaiTros = this.<VaiTro, QVaiTro>createSet("vaiTros", VaiTro.class, QVaiTro.class, PathInits.DIRECT2);

    public QNguoiDung(String variable) {
        this(NguoiDung.class, forVariable(variable), INITS);
    }

    public QNguoiDung(Path<? extends NguoiDung> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNguoiDung(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNguoiDung(PathMetadata metadata, PathInits inits) {
        this(NguoiDung.class, metadata, inits);
    }

    public QNguoiDung(Class<? extends NguoiDung> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QModel(type, metadata, inits);
        this.daXoa = _super.daXoa;
        this.id = _super.id;
        this.ngaySua = _super.ngaySua;
        this.ngayTao = _super.ngayTao;
        this.nguoiSua = _super.nguoiSua;
        this.nguoiTao = _super.nguoiTao;
        this.trangThai = _super.trangThai;
        this.vaiTro = inits.isInitialized("vaiTro") ? new QVaiTro(forProperty("vaiTro"), inits.get("vaiTro")) : null;
    }

}

