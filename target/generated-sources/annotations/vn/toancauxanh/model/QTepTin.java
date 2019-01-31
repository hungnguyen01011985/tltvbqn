package vn.toancauxanh.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTepTin is a Querydsl query type for TepTin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTepTin extends EntityPathBase<TepTin> {

    private static final long serialVersionUID = 983619473L;

    private static final PathInits INITS = new PathInits("*", "nguoiSua.*.*.*.*", "nguoiTao.*.*.*.*");

    public static final QTepTin tepTin = new QTepTin("tepTin");

    public final QModel _super;

    //inherited
    public final BooleanPath daXoa;

    //inherited
    public final NumberPath<Long> id;

    public final StringPath nameHash = createString("nameHash");

    //inherited
    public final DateTimePath<java.util.Date> ngaySua;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao;

    // inherited
    public final QNguoiDung nguoiSua;

    // inherited
    public final QNguoiDung nguoiTao;

    public final StringPath pathFile = createString("pathFile");

    public final StringPath tenFile = createString("tenFile");

    public final StringPath tenTaiLieu = createString("tenTaiLieu");

    //inherited
    public final StringPath trangThai;

    public final StringPath typeFile = createString("typeFile");

    public QTepTin(String variable) {
        this(TepTin.class, forVariable(variable), INITS);
    }

    public QTepTin(Path<? extends TepTin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTepTin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTepTin(PathMetadata metadata, PathInits inits) {
        this(TepTin.class, metadata, inits);
    }

    public QTepTin(Class<? extends TepTin> type, PathMetadata metadata, PathInits inits) {
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

