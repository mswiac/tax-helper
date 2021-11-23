package tax.helper.domain.core.common.command;

public interface Command<ReturnType> {

    ReturnType handle();
}
