package net.daniel.ChatRoom.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.daniel.ChatRoom.ChatRoom;
import net.daniel.ChatRoom.ChatRoomPlugin;
import net.daniel.ChatRoom.Lang;
import net.daniel.ChatRoom.data.InviteHolder;

public class MCUtils {

	public static void replaceAll(StringBuffer builder, String from, String to) {
		int index = builder.indexOf(from);
		while (index != -1) {
			builder.replace(index, index + from.length(), to);
			index += to.length(); // Move to the end of the replacement
			index = builder.indexOf(from, index);
		}
	}

	public static ChatRoom getChatRoom(Player player) {
		String name = ChatRoomPlugin.playerChatParties.get(player.getName().toLowerCase());
		if (name == null){
			return null;
		}else {			
			return ChatRoomPlugin.existRooms.get(name);
		}

	}
	

	public static ChatRoom getChatRoom(String player) {
		String name = ChatRoomPlugin.playerChatParties.get(player.toLowerCase());
		if (name == null){
			return null;
		}else {			
			return ChatRoomPlugin.existRooms.get(name);
		}

	}
	
	

	public static boolean isInteger(String num) {

		try {

			Integer.parseInt(num);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	
	public static boolean mustHaveRoom(Player player) {
		if(getChatRoom(player) != null) {
		
			return true;
		}else {
			player.sendMessage(Lang.PLAYER_HAS_NO_CHATROOM.toString());
			return false;
		}		
	}
	
	public static String getStringFromArgs(String[] args, int startIndex) {
		StringBuilder str = new StringBuilder();
		for (int i = startIndex; i < args.length; i++) {
			str.append(args[i] + " ");
		}

		String msg = ChatColor.translateAlternateColorCodes('&', str.toString());
		msg = removeLastChar(msg);
		return msg;

	}
	
	
	public static boolean mustBePlayer(CommandSender sender) {

		if(sender instanceof Player) {
			return true;

		}else {
			sender.sendMessage(Lang.ONLY_PLAYER.toString());
			return false;

		}
	}
	
	
	
	public static boolean mustBeLeader(Player player) {
		if(mustHaveRoom(player)) {
			if(getChatRoom(player).isLeader(player.getName().toLowerCase())) {
				return true;
			}else {
				player.sendMessage(Lang.ONLY_LEADER.toString());
				return false;
			}
		}
		return false;
	}

	
	public static boolean mustRoomHasThisP(CommandSender sender, String player, ChatRoom room) {
		if(room.hasThisPlayer(player)) {
			return true;
		}
		sender.sendMessage(Lang.NOT_MEMBER_TARGET.toString().replaceAll("%target%", player).replaceAll("%chatroom%", room.getName()));
		return false;
	}
	
	
	public static boolean mustRoomHasThisP(CommandSender sender, Player player, ChatRoom room) {
		return mustRoomHasThisP(sender, player.getName(), room);
	}
	
	public static void sendMsgTo(String target, String message) {
		Player p = Bukkit.getPlayerExact(target);
		if(p != null) {
			p.sendMessage(message);
		}
	}
	
	
	
	public static String removeLastChar(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		return str.substring(0, str.length() - 1);
	}
	
	
	public static void denyInvite(Player sender) {

		if (mustInvited(sender)) {
			InviteHolder invite = ChatRoomPlugin.invites.get(sender.getName().toLowerCase());
			sender.sendMessage(Lang.DENIED_INVITE_TARGET.toString());

			sendMsgTo(invite.getInviter(),
					Lang.DENIED_INVITE_INVITER.toString().replaceAll("%target%", sender.getName()));

			ChatRoomPlugin.invites.remove(sender.getName().toLowerCase());

		}

	}
	
	
	public static boolean toggleSpy(Player player) 
	{
		boolean result;
		String name = player.getName().toLowerCase();
		if(ChatRoomPlugin.spyPlayers.contains(player)) 
		{
			ChatRoomPlugin.spyPlayers.remove(player);
			ChatRoomPlugin.spyList.remove(name);
			result = false;
		}
		else
		{
			if(!ChatRoomPlugin.spyList.contains(name)) {
				ChatRoomPlugin.spyList.add(name);
			}
			ChatRoomPlugin.spyPlayers.add(player);
			
			result = true;
		}
		ChatRoomPlugin.ChatRoomYML.data.set("spy", ChatRoomPlugin.spyList);
		return result;
	}
	

	public static void acceptInvite(Player sender) {

		if (mustInvited(sender)) {

			InviteHolder invite = ChatRoomPlugin.invites.get(sender.getName().toLowerCase());

			if (MCUtils.getChatRoom(sender) == null) {
				invite.getRoom().addMember(sender.getName());

				sender.sendMessage(Lang.ACCEPTED_INVITE_TARGET.toString());

				sendMsgTo(invite.getInviter(),
						Lang.ACCEPT_INVITE_INVITER.toString().replaceAll("%target%", sender.getName()));

				invite.getRoom().sendBroadMessage(Lang.JOIN_CHATROOM.toString().replaceAll("%player%", sender.getName()));
				
				
			} else {
				sender.sendMessage(Lang.ALREADY_HAS_CHATROOM_ME.toString());
			}

			ChatRoomPlugin.invites.remove(sender.getName().toLowerCase());

		}

	}
	
	
	
	public static boolean mustInvited(Player player) {

		if (ChatRoomPlugin.invites.get(player.getName().toLowerCase()) != null) {

			return true;
		} else {

			player.sendMessage(Lang.NOT_INVITED.toString());
			return false;
		}

	}

	public static void inviteAutoExpire(String inviter, String target, Player inviterP) {

		if(mustBeOnline(target, inviterP)) {
			Player tar = Bukkit.getPlayerExact(target);

			InviteHolder temp = ChatRoomPlugin.invites.get(target.toLowerCase()); //null

			if (temp == null) {

				if (ChatRoomPlugin.invites.get(target.toLowerCase()) == null) {

					if (MCUtils.mustBeLeader(inviterP)) {

						if (ChatRoomPlugin.playerChatParties.get(target.toLowerCase()) == null) {

							ChatRoom room = MCUtils.getChatRoom(inviterP);
							String Name = room.getName();
							ChatRoomPlugin.invites.put(target.toLowerCase(),
									new InviteHolder(target, room, Name, inviter));

							inviterP.sendMessage(Lang.INVITED_PLAYER.toString().replaceAll("%target%", target));
							tar.sendMessage(
									Lang.INVITED_INFO_TO_TARGET.toString().replaceAll("%chatroom%", room.getName())
											.replaceAll("%sec%", String.valueOf(ChatRoomPlugin.invite_Sec)));

							(new BukkitRunnable() {
								public void run() {

									if (ChatRoomPlugin.invites.get(target.toLowerCase()) != null) {

										ChatRoomPlugin.invites.remove(target.toLowerCase());

										sendMsgTo(target, Lang.INVITE_DENIED_BY_EXPIRED_TARGET.toString());
										sendMsgTo(inviter, Lang.INVITE_DENIED_BY_EXPIRED.toString()
												.replaceAll("%target%", target));

									}

								}

							}).runTaskLaterAsynchronously(ChatRoomPlugin.plugin, 20L * ChatRoomPlugin.invite_Sec);

						} else {
							inviterP.sendMessage(Lang.ALREADY_HAS_CHATROOM.toString().replaceAll("%target%", target));
						}

					}
				} else {
					inviterP.sendMessage(Lang.ALREADY_INVITED.toString());

				}
			} else {

				if (temp.getInviter().equalsIgnoreCase(inviter)) {
					inviterP.sendMessage(Lang.ALREADY_INVITED.toString().replaceAll("%target%", target));
				} else {
					inviterP.sendMessage(Lang.INVITE_REQUESTED_BY_OTHER.toString().replaceAll("%target%", target)
							.replaceAll("%other%", temp.getInviter()));
				}

			}
		}
		

	}

	public static boolean mustBeOnline(String name, CommandSender sender) {
		if (Bukkit.getPlayerExact(name) != null) {
			return true;
		} else {
			sender.sendMessage(Lang.PLAYER_NOT_ONLINE.toString().replaceAll("%target%", name));
			return false;
		}

	}

	public static void broadcast(String msg) {
		Bukkit.getScheduler().runTask(ChatRoomPlugin.plugin, new Runnable() {
		    @Override
		    public void run() {
		        Bukkit.broadcastMessage(msg);
		        //Bukkit.broadcastMessage is not thread-safe for async
		    }
		});
	}
	
	
	
	

}
