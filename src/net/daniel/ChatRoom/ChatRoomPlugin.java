
package net.daniel.ChatRoom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.daniel.ChatRoom.Utils.FileUtils;
import net.daniel.ChatRoom.Utils.MCUtils;
import net.daniel.ChatRoom.Utils.ThrowsRunnable;
import net.daniel.ChatRoom.command.ChatCommand;
import net.daniel.ChatRoom.command.ChatRoomCommand;
import net.daniel.ChatRoom.command.ManageCommand;
import net.daniel.ChatRoom.command.SendchatCommand;
import net.daniel.ChatRoom.data.DataYML;
import net.daniel.ChatRoom.data.InviteHolder;

public class ChatRoomPlugin extends JavaPlugin {
	public static ChatRoomPlugin plugin;

	// 대소문자 구분 문제 (이름은 둘다 항상 소문자로 저장)
	public static HashMap<String, ChatRoom> existRooms;

	// player,ChatRoom
	public static HashMap<String, String> playerChatParties;

	public static HashMap<String, InviteHolder> invites;

	public static ArrayList<String> chatroomModenames;
	public static ArrayList<Player> spyPlayers;

	// 대소문자 구분 문제해결은 추후 UUID지원으로 변경 /

	public static DataYML ChatRoomYML;

	public static String chatFormat;
	public static ChatColor config_messageColor;

	public static int room_per_page;

	public static int invite_Sec;
	public static double autoSave;
	public static List<String> spyList;

	private final FileConfiguration langConfig = new YamlConfiguration();

	public void onEnable() {
		ChatRoomPlugin.plugin = this;
		// copy default config
		getConfig().options().copyDefaults(true);
		saveConfig();

		ChatRoomYML = new DataYML();
		ChatRoomYML.setup();
		existRooms = new HashMap<String, ChatRoom>();
		playerChatParties = new HashMap<String, String>();
		chatroomModenames = new ArrayList<String>();
		spyPlayers = new ArrayList<Player>();
		invites = new HashMap<String, InviteHolder>();
		this.reloadConfiguration();
		loadChatRoomData();

		getCommand("g").setExecutor(new SendchatCommand());
		getCommand("채팅방").setExecutor(new ChatRoomCommand());
		getCommand("chat").setExecutor(new ChatCommand());
		getCommand("채팅방관리").setExecutor(new ManageCommand());
	
		

		getServer().getPluginManager().registerEvents(new PlayerEventHandler(), this);
		
	}

	public void onDisable() {
		saveLangConfigurations();
		saveDataConfigurations();
	}


	public File createLangFile() {
		return new File(getDataFolder(), "lang.yml");
	}

	public void loadLangConfiguration() {

		doInputOutput(() -> langConfig.load(createLangFile()), "Exception while lang load.");

	}

	public void loadChatRoomData() {

		ChatRoomYML.reloadRooms();

		playerChatParties.clear();
		existRooms.clear();

		if (ChatRoomYML.data.getConfigurationSection("players") == null) {
			ChatRoomYML.data.createSection("players");
		}

		if (ChatRoomYML.data.getConfigurationSection("ChatRooms") == null) {
			ChatRoomYML.data.createSection("ChatRooms");

		}

		ConfigurationSection playerSection = ChatRoomYML.data.getConfigurationSection("players");

		for (String name : playerSection.getKeys(false)) {

			playerChatParties.put(name.toLowerCase(), playerSection.getString(name));
		}

		ConfigurationSection ChatRoomSection = ChatRoomYML.data.getConfigurationSection("ChatRooms");

		for (String name : ChatRoomSection.getKeys(false)) {

			existRooms.put(name.toLowerCase(), loadChatRoomFromYML(name.toLowerCase()));

		}

		spyList = ChatRoomPlugin.ChatRoomYML.data.getStringList("spy");

		spyPlayers.clear();

		for (Player player : Bukkit.getOnlinePlayers()) {
			registerSpy(player);
			ChatRoom party = MCUtils.getChatRoom(player);

			if (party == null) {
				plugin.removeChatRoomMode(player);
			} else {
				party.activePlayers.add(player);
			}

		}

	}

	public void reloadConfiguration() {

		reloadConfig();

		getConfig().options().copyDefaults(true);

		saveConfig();

		chatFormat = getConfig().getString("chatFormat", "&a[PartyN] &f{DISPLAYNAME}&f: {MESSAGE}");
		if (!chatFormat.contains("") || !chatFormat.contains("DISPLAYNAME")) {
			System.out.println("chatFormat에 {DISPLAYNAME} 또는 {MESSAGE}가 누락되어 있어 기본값으로 설정됨");
			chatFormat = "&a[P] &f{DISPLAYNAME}&f: {MESSAGE}";
		}
		
		chatFormat = ChatColor.translateAlternateColorCodes('&', chatFormat);

		config_messageColor = ChatColor.getByChar(getConfig().getString("messageColor").substring(1));
		if (config_messageColor == null)
			config_messageColor = ChatColor.WHITE;

		room_per_page = getConfig().getInt("rooms-per-page", 15);

		if (room_per_page < 1) {
			System.out.println("rooms-per-page 값은 0보다 큰 정수여야 합니다.");
			room_per_page = 15;
		}

		invite_Sec = getConfig().getInt("invite_sec", 30);

		if (invite_Sec < 1) {
			System.out.println("invite_sec 값은 0보다 큰 정수여야 합니다.");
			invite_Sec = 30;
		}

		autoSave = getConfig().getDouble("auto-save", 90.0);

		if (autoSave < 1) {
			System.out.println("auto-save 값은 1이상의 숫자여야 합니다. (단위 초)");
			autoSave = 90.0;
		}

		loadLangConfiguration();
		Lang.init(langConfig);
		saveLangConfigurations();
		
		//AutoSaver.register(this);
		Bukkit.getScheduler().cancelTasks(this);		
	}

	public void saveLangConfigurations() {

		doInputOutput(() -> langConfig.save(FileUtils.writeEnsure(createLangFile())), "Exception when lang save.");
	}

	public void saveDataConfigurations() {

		ChatRoomYML.saveData();
	}

	private void doInputOutput(ThrowsRunnable runnable, String errorMessage) {
		try {
			runnable.run();
		} catch (FileNotFoundException ex) {
			// Ignore
		} catch (Exception ex) {
			getLogger().log(Level.WARNING, errorMessage, ex);
		}
	}

	public void Restart() {
		getServer().getPluginManager().disablePlugin(this);
		getServer().getPluginManager().enablePlugin(this);
	}

	public ChatRoom getPlayerChatRoomFromYML(Player player) {
		String ChatRoomName = ChatRoomYML.data.getConfigurationSection("players").getString(player.getName().toLowerCase());
		if (ChatRoomName != null) {
			return loadChatRoomFromYML(ChatRoomName);
		} else {
			return null;
		}
	}

	public ChatRoom loadChatRoomFromYML(String name) {
		ChatRoom ChatRoom = existRooms.get(name);

		if (ChatRoom == null) {
			ChatRoom = new ChatRoom(name);

			ConfigurationSection ChatRoomSection = ChatRoomYML.data.getConfigurationSection("ChatRooms." + name);

			if (ChatRoomSection == null) {

				return null;
			}

			ChatRoom.setLeaders((ArrayList<String>) ChatRoomSection.getStringList("leaders"));
			ChatRoom.setMembers((ArrayList<String>) ChatRoomSection.getStringList("members"));

		}
		return ChatRoom;
	}

	public void deleteChatRoom(ChatRoom ChatRoom) {

		for (String playerName : ChatRoom.getLeaders()) {
			removePlayer(playerName);

		}
		for (String playerName : ChatRoom.getMembers()) {
			removePlayer(playerName);
		}
		existRooms.remove(ChatRoom.getName());

		ChatRoomYML.data.getConfigurationSection("ChatRooms").set(ChatRoom.getName(), null);
		ChatRoom = null;
	}

	public void updatePlayer(String player) {

		player = player.toLowerCase();
		String ChatRoomName = playerChatParties.get(player);

		if (ChatRoomName != null) {
			ChatRoomYML.data.set("players." + player, ChatRoomName.toLowerCase());

		} else {
			ChatRoomYML.data.set("players." + player, null);

		}

	}

	public void removePlayer(String playerName) {

		if (Bukkit.getPlayerExact(playerName) != null) {

			Player p = Bukkit.getPlayerExact(playerName);

			ChatRoom room = MCUtils.getChatRoom(p);
			if (room != null) {
				room.activePlayers.remove(p);
			}

		}

		chatroomModenames.remove(playerName.toLowerCase());
		playerChatParties.remove(playerName.toLowerCase());
		ChatRoomYML.data.getConfigurationSection("players").set(playerName.toLowerCase(), null);

	}

	public void addChatRoomMode(Player p) {
		addChatRoomMode(p.getName().toLowerCase());
	}

	public void addChatRoomMode(String Name) {
		chatroomModenames.add(Name.toLowerCase());
	}

	public void removeChatRoomMode(Player p) {
		addChatRoomMode(p.getName().toLowerCase());
	}

	public void removeChatRoomMode(String Name) {
		chatroomModenames.remove(Name.toLowerCase());
	}

	public FileConfiguration getLangConfig() {
		return langConfig;
	}

	public void registerSpy(Player player) {
		if (getConfig().getStringList("spy").contains(player.getName().toLowerCase())) {
			spyPlayers.add(player);
		}
	}

	public void unregisterSpy(Player player) {
		spyPlayers.remove(player);
	}

	public void sendSpyPartyMessage(ChatRoom room, String message) {
		for (Player player : spyPlayers) {
			if (player.hasPermission("ChatRoom.admin")
					&& !isSameRoom(room, player)  ) {
				player.sendMessage(ChatColor.GRAY + "[" + room.getName() + "] " + message);
			}
		}
		getLogger().info("[" + room.getName() + "] " + message);
	}
	
	public boolean isSameRoom(String room, Player player) {

		ChatRoom chatroom = MCUtils.getChatRoom(player);
		if (chatroom == null) {
			return false;
		} else {
			return chatroom.getName().equalsIgnoreCase(room);
		}

	}
	
	public boolean isSameRoom(ChatRoom room, Player player) {
		return isSameRoom(room.getName(), player);
			
	}


	public void sendSpyChatMessage(ChatRoom party, Player sender, String message) {
		sendSpyPartyMessage(party, sender.getName() + ": " + message);
	}

	public void sendBroadtoSpy(ChatRoom party, String message) {
		for (Player player : spyPlayers) {
			if (player != null) {

				if (player.hasPermission("ChatRoom.admin")
						&& !party.getName().equalsIgnoreCase(MCUtils.getChatRoom(player).getName())) {
					player.sendMessage(ChatColor.GRAY + "[" + party.getName() + "] " + message);
				}

			}

		}
		getLogger().info("[" + party.getName() + "] " + message);
	}

	public static void createChatRoom(String name, String owner) {
		if (existRooms.get(name.toLowerCase()) == null) {

			if (playerChatParties.get(owner.toLowerCase()) == null) {
				ChatRoom room = new ChatRoom(name.toLowerCase());
				room.addLeader(owner.toLowerCase());
				existRooms.put(name.toLowerCase(), room);
				playerChatParties.put(owner.toLowerCase(), room.getName());
				room.saveChatRoom();
				Player p = Bukkit.getPlayerExact(owner);
				if (p != null) {
					room.activePlayers.add(p);
				}

			}

		}
	}

	public static void createChatRoom(String name, Player owner) {
		createChatRoom(name, owner.getName().toLowerCase());
	}

}
