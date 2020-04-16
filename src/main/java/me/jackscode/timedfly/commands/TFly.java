package me.jackscode.timedfly.commands;

import me.jackscode.timedfly.api.Command;
import me.jackscode.timedfly.enums.CommandType;
import me.jackscode.timedfly.handlers.CommandHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TFly implements CommandExecutor {

    private final CommandHandler commandHandler;

    public TFly(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull org.bukkit.command.Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        List<Command> commands = this.commandHandler
                .getCommands()
                .stream()
                .filter(cmd -> cmd.getCommandType() == CommandType.TFLY)
                .collect(Collectors.toList());

        if (commands.isEmpty()) return true;

        if (args.length == 0) return true;

        commands.stream()
                .filter(cmd -> cmd.getName().equals(args[0]))
                .forEach(cmd -> cmd.execute(sender, Arrays.copyOfRange(args, 1, args.length)));

        return true;
    }
}