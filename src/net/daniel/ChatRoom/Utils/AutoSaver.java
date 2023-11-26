package net.daniel.ChatRoom.Utils;


import net.daniel.ChatRoom.ChatRoomPlugin;

public class AutoSaver implements Runnable {


	public AutoSaver() {
	}

	@Override
	public void run() {
		try {
			ChatRoomPlugin.ChatRoomYML.saveData();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
