package com.licenta.vote.cmd.infrasctructure;

import com.licenta.cqrs.core.commands.BaseCommand;
import com.licenta.cqrs.core.commands.CommandHandlerMethod;
import com.licenta.cqrs.core.inrastructure.CommandDispacher;
import org.springframework.stereotype.Service;
import com.licenta.vote.CustomExceptions.MultipleCommandHandlersException;
import com.licenta.vote.CustomExceptions.NoCommandHandlerException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
public class InvitesCommandDispacher implements CommandDispacher {
    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();
    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand command) {
        var handlers = routes.get(command.getClass());
        if (handlers == null || handlers.size() == 0) {
            throw new NoCommandHandlerException("No command handler was registered!");
        }
        if (handlers.size() > 1) {
            throw new MultipleCommandHandlersException("Cannot send command to more than one handler!");
        }
        handlers.get(0).handle(command);
    }
}