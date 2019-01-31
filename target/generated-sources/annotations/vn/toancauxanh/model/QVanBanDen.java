package vn.toancauxanh.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVanBanDen is a Querydsl query type for VanBanDen
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVanBanDen extends EntityPathBase<VanBanDen> {

    private static final long serialVersionUID = -1553040502L;

    private static final PathInits INITS = new PathInits("*", "nguoiSua.*.*.*.*", "nguoiTao.*.*.*.*");

    public static final QVanBanDen vanBanDen = new QVanBanDen("vanBanDen");

    public final QModel _super;

    public final QCoQuanToChuc coQuanToChuc;

    public final BooleanPath daNhan = createBoolean("daNhan");

    //inherited
    public final BooleanPath daXoa;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DateTimePath<java.util.Date> ngaySua;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao;

    // inherited
    public final QNguoiDung nguoiSua;

    // inherited
    public final QNguoiDung nguoiTao;

    //inherited
    public final StringPath trangThai;

    public final QVanBan vanBan;

    public QVanBanDen(String variable) {
        this(VanBanDen.class, forVariable(variable), INITS);
    }

    public QVanBanDen(Path<? extends VanBanDen> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVanBanDen(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVanBanDen(PathMetadata metadata, PathInits inits) {
        this(VanBanDen.class, metadata, inits);
    }

    public QVanBanDen(Class<? extends VanBanDen> type, PathMetadata metadata, PathInits inits) {
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
        this.vanBan = inits.isInitialized("vanBan") ? new QVanBan(forProperty("vanBan"), inits.get("vanBan")) : null;
    }

}

