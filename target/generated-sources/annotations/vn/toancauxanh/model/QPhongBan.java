package vn.toancauxanh.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhongBan is a Querydsl query type for PhongBan
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPhongBan extends EntityPathBase<PhongBan> {

    private static final long serialVersionUID = 347029078L;

    private static final PathInits INITS = new PathInits("*", "nguoiSua.*.*.*.*", "nguoiTao.*.*.*.*");

    public static final QPhongBan phongBan = new QPhongBan("phongBan");

    public final QModel _super;

    //inherited
    public final BooleanPath daXoa;

    //inherited
    public final NumberPath<Long> id;

    public final StringPath moTa = createString("moTa");

    //inherited
    public final DateTimePath<java.util.Date> ngaySua;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao;

    // inherited
    public final QNhanVien nguoiSua;

    // inherited
    public final QNhanVien nguoiTao;

    public final StringPath ten = createString("ten");

    //inherited
    public final StringPath trangThai;

    public QPhongBan(String variable) {
        this(PhongBan.class, forVariable(variable), INITS);
    }

    public QPhongBan(Path<? extends PhongBan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhongBan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhongBan(PathMetadata metadata, PathInits inits) {
        this(PhongBan.class, metadata, inits);
    }

    public QPhongBan(Class<? extends PhongBan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QModel(type, metadata, inits);
        this.daXoa = _super.daXoa;
        this.id = _super.id;
        this.ngaySua = _super.ngaySua;
        this.ngayTao = _super.ngayTao;
        this.nguoiSua = _super.nguoiSua;
        this.nguoiTao = _super.nguoiTao;
        this.trangThai = _super.trangThai;
    }

}

