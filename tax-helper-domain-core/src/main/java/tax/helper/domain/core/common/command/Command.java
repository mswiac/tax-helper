package tax.helper.domain.core.common.command;

public interface Command<T> {

    T handle();
}
