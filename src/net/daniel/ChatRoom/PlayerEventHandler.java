package net.daniel.ChatRoom;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.daniel.ChatRoom.Utils.MCUtils;

public class PlayerEventHandler implements Listener {

	private ChatRoomPlugin plugin;

	public PlayerEventHandler() {
		this.plugin = ChatRoomPlugin.plugin;
	}

	// when a player successfully joins the server...
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		plugin.registerSpy(player);

		ChatRoom party = MCUtils.getChatRoom(player);

		if (party == null) {
			plugin.removeChatRoomMode(player);
		} else {
			party.activePlayers.add(player);

		}

	}

	// when a player quits...
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		ChatRoom party = MCUtils.getChatRoom(player);
		if (party != null) {
			party.activePlayers.remove(player);
		} else {
			plugin.removeChatRoomMode(player);

		}

		plugin.unregisterSpy(player);
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		String uuid = player.getUniqueId().toString();

		if (ChatRoomPlugin.chatroomModenames.contains(uuid)) {

			String message = event.getMessage();
			event.setCancelled(true);

			ChatRoom room = MCUtils.getChatRoom(player);

			if (room != null) {

				room.sendPlayerMessage(player, message);

				plugin.sendSpyChatMessage(room, player, message);

			} else {
				plugin.removeChatRoomMode(player);
				player.sendMessage(Lang.NOT_HAVE_CHATROOM.toString());
			}


		} 

		
		}



}
