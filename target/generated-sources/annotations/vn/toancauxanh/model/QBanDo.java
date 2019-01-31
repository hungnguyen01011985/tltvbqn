package vn.toancauxanh.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBanDo is a Querydsl query type for BanDo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBanDo extends EntityPathBase<BanDo> {

    private static final long serialVersionUID = 153532035L;

    private static final PathInits INITS = new PathInits("*", "nguoiSua.*.*.*.*", "nguoiTao.*.*.*.*");

    public static final QBanDo banDo = new QBanDo("banDo");

    public final QModel _super;

    //inherited
    public final BooleanPath daXoa;

    //inherited
    public final NumberPath<Long> id;

    public final NumberPath<Double> lat = createNumber("lat", Double.class);

    public final NumberPath<Double> lng = createNumber("lng", Double.class);

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

    public QBanDo(String variable) {
        this(BanDo.class, forVariable(variable), INITS);
    }

    public QBanDo(Path<? extends BanDo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBanDo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBanDo(PathMetadata metadata, PathInits inits) {
        this(BanDo.class, metadata, inits);
    }

    public QBanDo(Class<? extends BanDo> type, PathMetadata metadata, PathInits inits) {
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

