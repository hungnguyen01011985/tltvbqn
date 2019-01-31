package vn.toancauxanh.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVanBan is a Querydsl query type for VanBan
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVanBan extends EntityPathBase<VanBan> {

    private static final long serialVersionUID = 1037106563L;

    private static final PathInits INITS = new PathInits("*", "nguoiSua.*.*.*.*", "nguoiTao.*.*.*.*");

    public static final QVanBan vanBan = new QVanBan("vanBan");

    public final QModel _super;

    public final StringPath checkSum = createString("checkSum");

    public final StringPath code = createString("code");

    public final QCoQuanToChuc coQuanToChuc;

    //inherited
    public final BooleanPath daXoa;

    public final StringPath docId = createString("docId");

    public final StringPath documentId = createString("documentId");

    public final StringPath fileUrl = createString("fileUrl");

    //inherited
    public final NumberPath<Long> id;

    public final StringPath loaiLienThong = createString("loaiLienThong");

    public final DateTimePath<java.util.Date> ngayPhatHanh = createDateTime("ngayPhatHanh", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> ngaySua;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao;

    // inherited
    public final QNhanVien nguoiSua;

    // inherited
    public final QNhanVien nguoiTao;

    public final NumberPath<Integer> soFileDinhKem = createNumber("soFileDinhKem", Integer.class);

    public final StringPath soKyhieu = createString("soKyhieu");

    public final StringPath subject = createString("subject");

    //inherited
    public final StringPath trangThai;

    public QVanBan(String variable) {
        this(VanBan.class, forVariable(variable), INITS);
    }

    public QVanBan(Path<? extends VanBan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVanBan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVanBan(PathMetadata metadata, PathInits inits) {
        this(VanBan.class, metadata, inits);
    }

    public QVanBan(Class<? extends VanBan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QModel(type, metadata, inits);
        this.coQuanToChuc = inits.isInitialized("coQuanToChuc") ? new QCoQuanToChuc(forProperty("coQuanToChuc"), inits.get("coQuanToChuc")) : null;
        this.daXoa = _super.daXoa;
        this.id = _super.id;
        this.ngaySua = _super.ngaySua;
        this.ngayTao = _super.ngayTao;
        this.nguoiSua = _super.nguoiSua;
        this.nguoiTao = _super.nguoiTao;
        this.trangThai = _super.trangThai;
    }

}

