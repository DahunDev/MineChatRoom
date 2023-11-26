package net.daniel.ChatRoom.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.daniel.ChatRoom.ChatRoomPlugin;
import net.daniel.ChatRoom.Lang;
import net.daniel.ChatRoom.Utils.MCUtils;

public class ChatCommand implements CommandExecutor {

	// chat

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (MCUtils.mustBePlayer(sender)) {

			Player player = (Player) sender;

			if (sender.hasPermission("ChatRoom.user")) {

				String uuid = player.getUniqueId().toString();
				if (ChatRoomPlugin.chatroomModenames.contains(uuid)) {
					ChatRoomPlugin.chatroomModenames.remove(uuid);

					sender.sendMessage(Lang.GLOBAL_CHAT_TOGGLED.toString());
				} else {
					ChatRoomPlugin.chatroomModenames.add(uuid);
					sender.sendMessage(Lang.CHATROOM_TOGGLED.toString());
				}

			} else {
				sender.sendMessage(Lang.NO_PERM.toString());
			}

		}
		
		

		return true;
	}
}
