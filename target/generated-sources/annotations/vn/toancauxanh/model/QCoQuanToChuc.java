package vn.toancauxanh.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCoQuanToChuc is a Querydsl query type for CoQuanToChuc
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCoQuanToChuc extends EntityPathBase<CoQuanToChuc> {

    private static final long serialVersionUID = 1097182050L;

    private static final PathInits INITS = new PathInits("*", "nguoiSua.*.*.*.*", "nguoiTao.*.*.*.*");

    public static final QCoQuanToChuc coQuanToChuc = new QCoQuanToChuc("coQuanToChuc");

    public final QModel _super;

    public final QCoQuanToChuc cha;

    //inherited
    public final BooleanPath daXoa;

    public final StringPath email = createString("email");

    public final StringPath fax = createString("fax");

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

    public final BooleanPath noiBo = createBoolean("noiBo");

    public final StringPath organAdd = createString("organAdd");

    public final StringPath organId = createString("organId");

    public final StringPath organizationCharge = createString("organizationCharge");

    public final StringPath organName = createString("organName");

    public final StringPath telephone = createString("telephone");

    //inherited
    public final StringPath trangThai;

    public final StringPath website = createString("website");

    public QCoQuanToChuc(String variable) {
        this(CoQuanToChuc.class, forVariable(variable), INITS);
    }

    public QCoQuanToChuc(Path<? extends CoQuanToChuc> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCoQuanToChuc(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCoQuanToChuc(PathMetadata metadata, PathInits inits) {
        this(CoQuanToChuc.class, metadata, inits);
    }

    public QCoQuanToChuc(Class<? extends CoQuanToChuc> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QModel(type, metadata, inits);
        this.cha = inits.isInitialized("cha") ? new QCoQuanToChuc(forProperty("cha"), inits.get("cha")) : null;
        this.daXoa = _super.daXoa;
        this.id = _super.id;
        this.ngaySua = _super.ngaySua;
        this.ngayTao = _super.ngayTao;
        this.nguoiSua = _super.nguoiSua;
        this.nguoiTao = _super.nguoiTao;
        this.trangThai = _super.trangThai;
    }

}

