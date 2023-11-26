package net.daniel.ChatRoom.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.daniel.ChatRoom.ChatRoomPlugin;

public class DataYML {

	public FileConfiguration data;
	public File dataFile;
	
		

	public void setup() {
		if (!ChatRoomPlugin.plugin.getDataFolder().exists()) {
			ChatRoomPlugin.plugin.getDataFolder().mkdir();
		}

		dataFile = new File(ChatRoomPlugin.plugin.getDataFolder(), "data.yml");

		if (!dataFile.exists()) {
			try {
				dataFile.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The data.yml file has been created");
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender()
						.sendMessage(ChatColor.RED + "Could not create the data.yml file in MineChatRooms");
			}
		}

		data = YamlConfiguration.loadConfiguration(dataFile);
	}

	public void reloadRooms() {
		data = YamlConfiguration.loadConfiguration(dataFile);
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "The data.yml file has been reload");

	}
	
	
	
	
	public void saveData() {

		data.set("players", null);
		data.set("ChatRooms", null);

		for (String com : ChatRoomPlugin.existRooms.keySet()) {

			ChatRoomPlugin.existRooms.get(com).saveChatRoom();

		}

		for (String player : ChatRoomPlugin.playerChatParties.keySet()) {
			ChatRoomPlugin.plugin.updatePlayer(player);

		}

		data.set("spy", ChatRoomPlugin.spyList);

		
		try {
			data.save(dataFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
