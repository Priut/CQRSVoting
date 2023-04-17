package com.licenta.cqrs.core.inrastructure;

import com.licenta.cqrs.core.commands.BaseCommand;
import com.licenta.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispacher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
