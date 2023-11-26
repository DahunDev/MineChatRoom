
package net.daniel.ChatRoom;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class ChatRoom {

	private String name;
	private ArrayList<String> members;
	private ArrayList<String> leaders;
	public ArrayList<Player> activePlayers;

	public ChatRoom(String name) {

		this.name = name.toLowerCase();
		setMembers(new ArrayList<String>());
		setLeaders(new ArrayList<String>());
		activePlayers = new ArrayList<Player>();
	}

	public boolean isMember(String player) {
		return this.getMembers().contains(player.toLowerCase());
	}

	public boolean isMember(Player player) {
		return this.getMembers().contains(player.getName().toLowerCase());
	}
	
	

	public void sendPlayerMessage(Player sender, String message) {
		
		String formattedMessage = ChatRoomPlugin.chatFormat.replace("{DISPLAYNAME}", sender.getDisplayName())
				.replace("{PARTYNAME}", this.name).replace("{MESSAGE}", message);
		for (Player player : activePlayers) {
			if (player.hasPermission("ChatRoom.user")) {
		
				player.sendMessage(formattedMessage);
			}
		}
	}


	
	
	
	public void sendBroadMessage(String message) {
		for (Player player : activePlayers) {
			if (player.hasPermission("ChatRoom.user")) {
				player.sendMessage(message);
			}
		}

	}

	public static boolean hasChatRoom(String playername) {
		playername = playername.toLowerCase();
		String Name = ChatRoomPlugin.playerChatParties.get(playername);
		if (Name == null || Name.length() < 1) {
			return false;

		} else {
			return true;
		}

	}

	public boolean isLeader(String player) {
		return this.getLeaders().contains(player.toLowerCase());
	}

	public boolean hasThisPlayer(String player) {
		return (this.isMember(player) || this.isLeader(player));
	}

	public String getName() {
		return this.name;
	}

	public int getTeamSize() {

		return leaders.size() + members.size();

	}

	public boolean addMember(String playerName) {
		if (!hasChatRoom(playerName)) {
			getMembers().add(playerName.toLowerCase());
			ChatRoomPlugin.playerChatParties.put(playerName.toLowerCase(), this.name.toLowerCase());
			ChatRoomPlugin.plugin.updatePlayer(playerName);

			Player p = Bukkit.getPlayerExact(playerName);
			if (p != null) {
				activePlayers.add(p);
			}

			return true;
		} else {
			return false;
		}

	}

	public boolean setLeader(String playerName) {
		if (isMember(playerName)) {
			getLeaders().add(playerName.toLowerCase());
			getMembers().remove(playerName.toLowerCase());
			ChatRoomPlugin.plugin.updatePlayer(playerName);

			return true;
		}
		return false;

	}

	public void addLeader(String playerName) {
		getLeaders().add(playerName.toLowerCase());
		Player p = Bukkit.getPlayerExact(name);
		if (p != null) {

			activePlayers.remove(p);

		}
		ChatRoomPlugin.plugin.updatePlayer(playerName);

	}

	public void removeMember(String playerName) {

		playerName = playerName.toLowerCase();
		members.remove(playerName);
		ChatRoomPlugin.plugin.removePlayer(playerName);

		Player p = Bukkit.getPlayerExact(playerName);
		if (p != null) {

			activePlayers.remove(p);

		}

	}

	public void removeLeader(String playerName) {
		playerName = playerName.toLowerCase();

		getLeaders().remove(playerName);
		ChatRoomPlugin.plugin.removePlayer(playerName);

		Player p = Bukkit.getPlayerExact(playerName);
		if (p != null) {

			activePlayers.remove(p);

		}
	}

	public void saveChatRoom() {

		ChatRoomPlugin.ChatRoomYML.data.createSection("ChatRooms." + name);

		ConfigurationSection chatroomSection = ChatRoomPlugin.ChatRoomYML.data.getConfigurationSection("ChatRooms." + name);

		chatroomSection.set("leaders", this.getLeaders());

		chatroomSection.set("members", this.getMembers());

	}

	public ArrayList<String> getLeaders() {
		return leaders;
	}

	public ArrayList<String> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}

	public void setLeaders(ArrayList<String> leaders) {
		this.leaders = leaders;
	}

	public String getMemberList() {
		String list = String.valueOf(members);
		list = list.substring(1);
		list = list.substring(0, list.length() - 1);

		if (list == null || list.length() == 0) {
			return Lang.EMPTY_LIST.toString();
		}

		return list;

	}

	public String getLeaderList() {
		String list = String.valueOf(leaders);
		list = list.substring(1);
		list = list.substring(0, list.length() - 1);

		if (list == null || list.length() == 0) {
			return Lang.EMPTY_LIST.toString();
		}
		return list;
	}

}
