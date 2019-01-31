package vn.toancauxanh.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QThongBao is a Querydsl query type for ThongBao
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QThongBao extends EntityPathBase<ThongBao> {

    private static final long serialVersionUID = -1271664173L;

    private static final PathInits INITS = new PathInits("*", "nguoiSua.*.*.*.*", "nguoiTao.*.*.*.*");

    public static final QThongBao thongBao = new QThongBao("thongBao");

    public final QModel _super;

    public final BooleanPath daXem = createBoolean("daXem");

    //inherited
    public final BooleanPath daXoa;

    //inherited
    public final NumberPath<Long> id;

    public final NumberPath<Long> idObject = createNumber("idObject", Long.class);

    public final EnumPath<vn.toancauxanh.gg.model.enums.ThongBaoEnum> kieuThongBao = createEnum("kieuThongBao", vn.toancauxanh.gg.model.enums.ThongBaoEnum.class);

    public final EnumPath<vn.toancauxanh.gg.model.enums.LoaiThongBao> loaiThongBao = createEnum("loaiThongBao", vn.toancauxanh.gg.model.enums.LoaiThongBao.class);

    //inherited
    public final DateTimePath<java.util.Date> ngaySua;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao;

    public final QNhanVien nguoiGui;

    public final QNhanVien nguoiNhan;

    // inherited
    public final QNhanVien nguoiSua;

    // inherited
    public final QNhanVien nguoiTao;

    public final StringPath noiDung = createString("noiDung");

    //inherited
    public final StringPath trangThai;

    public final BooleanPath xemChiTiet = createBoolean("xemChiTiet");

    public QThongBao(String variable) {
        this(ThongBao.class, forVariable(variable), INITS);
    }

    public QThongBao(Path<? extends ThongBao> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QThongBao(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QThongBao(PathMetadata metadata, PathInits inits) {
        this(ThongBao.class, metadata, inits);
    }

    public QThongBao(Class<? extends ThongBao> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QModel(type, metadata, inits);
        this.daXoa = _super.daXoa;
        this.id = _super.id;
        this.ngaySua = _super.ngaySua;
        this.ngayTao = _super.ngayTao;
        this.nguoiGui = inits.isInitialized("nguoiGui") ? new QNhanVien(forProperty("nguoiGui"), inits.get("nguoiGui")) : null;
        this.nguoiNhan = inits.isInitialized("nguoiNhan") ? new QNhanVien(forProperty("nguoiNhan"), inits.get("nguoiNhan")) : null;
        this.nguoiSua = _super.nguoiSua;
        this.nguoiTao = _super.nguoiTao;
        this.trangThai = _super.trangThai;
    }

}

