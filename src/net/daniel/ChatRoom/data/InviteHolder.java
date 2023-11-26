package net.daniel.ChatRoom.data;
import net.daniel.ChatRoom.ChatRoom;

public class InviteHolder {

	
	private String target;
	private String inviter;

	private ChatRoom room;
	private	String roomName;
	//long time;
	
	public InviteHolder(String target, ChatRoom room, String roomName, String inviter) {
		this.target = target;
		this.inviter = inviter;
		this.room = room;
		this.roomName = roomName;
	//	time = System.currentTimeMillis();
		
	}

	public String getTarget() {
		return target;
	}

	public String getInviter() {
		return inviter;
	}

	public ChatRoom getRoom() {
		return room;
	}

	public String getRoomName() {
		return roomName;
	}

	
}
