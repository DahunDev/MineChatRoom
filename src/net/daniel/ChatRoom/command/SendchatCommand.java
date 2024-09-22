package net.daniel.ChatRoom.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.daniel.ChatRoom.ChatRoom;
import net.daniel.ChatRoom.ChatRoomPlugin;
import net.daniel.ChatRoom.Lang;
import net.daniel.ChatRoom.Utils.MCUtils;

public class SendchatCommand implements CommandExecutor {

    /// g

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!MCUtils.mustBePlayer(sender)) {
            return true;
        }


        if (!sender.hasPermission("ChatRoom.user")) {
            sender.sendMessage(Lang.NO_PERM.toString());
            return true;
        }
        Player player = (Player) sender;

        if (args.length >= 1) {
            if (MCUtils.mustHaveRoom(player)) {

                ChatRoom room = MCUtils.getChatRoom(player);

                String msg = MCUtils.getStringFromArgs(args, 0);

                room.sendPlayerMessage(player, msg);
                ChatRoomPlugin.plugin.sendSpyChatMessage(room, player, msg);
                return true;

            }
        } else {
            sender.sendMessage(Lang.CHATROOM_MSG_CMD_HELP.toString());
        }

        return true;
    }
}
