package tax.helper.domain.core.common.query;

public interface Query<ReturnType> {

    ReturnType handle();
}