package net.daniel.ChatRoom.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.daniel.ChatRoom.ChatRoom;
import net.daniel.ChatRoom.ChatRoomPlugin;
import net.daniel.ChatRoom.Lang;
import net.daniel.ChatRoom.Utils.MCUtils;

public class ChatRoomCommand implements CommandExecutor, TabCompleter {

	ChatRoomPlugin plugin = ChatRoomPlugin.plugin;

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> list = new ArrayList<>();

		if (args.length < 2) {

			list.add("정보");
			list.add("생성");
			list.add("목록");
			list.add("수락");
			list.add("거절");
			list.add("초대");
			list.add("지도자");
			list.add("조회");
			list.add("나가기");
			list.add("추방");

			String finalArg = args[args.length - 1];
			Iterator<String> it = list.iterator();
			while (it.hasNext()) {
				if (!it.next().startsWith(finalArg)) {
					it.remove();
				}
			}

			return list;
		} else {
			return null; // Default completion

		}

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.hasPermission("ChatRoom.user")) {

			if (args.length >= 1) {

				new BukkitRunnable() {

					@Override
					public void run() {
						switch (args[0]) {
						case "정보":
							if (args.length == 2) {

								ChatRoom room = ChatRoomPlugin.existRooms.get(args[1].toLowerCase());

								if (room == null) {
									sender.sendMessage(
											Lang.CHATROOM_NOTEXIST.toString().replaceAll("%chatroom%", args[1]));
								} else {

									sender.sendMessage(Lang.withPlaceHolder(Lang.CHATROOM_INFO,
											new String[] { "%chatroom%", "%leaders%", "%members%", "%size%" },
											room.getName(), room.getLeaderList(), room.getMemberList(),
											room.getTeamSize()));

								}

							} else if (args.length == 1) {

								if (sender instanceof Player) {

									Player p = (Player) sender;

									ChatRoom room = MCUtils.getChatRoom(p);

									if (room != null) {
										sender.sendMessage(Lang.withPlaceHolder(Lang.CHATROOM_INFO_MINE,
												new String[] { "%chatroom%", "%leaders%", "%members%", "%size%" },
												room.getName(), room.getLeaderList(), room.getMemberList(),
												room.getTeamSize()));
									} else {
										sender.sendMessage(Lang.PLAYER_HAS_NO_CHATROOM.toString());

									}

								} else {
									sender.sendMessage(Lang.ONLY_PLAYER.toString());

								}

							} else {

								sender.sendMessage(Lang.CHATROOM_INFO_HELP.toString());

							}

							break;
						case "생성":
							if (sender instanceof Player) {

								Player p = (Player) sender;

								if (args.length == 2) {
									if (ChatRoomPlugin.existRooms.get(args[1].toLowerCase()) == null) {

										if (MCUtils.getChatRoom(p) == null) {

											String name = ChatColor.translateAlternateColorCodes('&', args[1]);
										
											int size = ChatColor.stripColor(name).length();

											if (size == args[1].length()) {

												if (!((args[1].contains(".") || args[1].contains(",")
														|| args[1].contains("#") || args[1].contains("$")))) {
													ChatRoomPlugin.createChatRoom(args[1], p);

													sender.sendMessage(Lang.CREATED_CHATROOM.toString()
															.replaceAll("%chatroom%", args[1].toLowerCase()));

												} else {
													sender.sendMessage(Lang.CONTAINS_NOALLOWED_CHAR.toString());
												}

											} else {
												sender.sendMessage(Lang.CONTAINS_NOALLOWED_CHAR.toString());

											}

										} else {
											sender.sendMessage(Lang.ALREADY_HAS_CHATROOM_ME.toString());
										}

									} else {
										sender.sendMessage(Lang.CHATROOM_ALREADY_EXIST.toString()
												.replaceAll("%chatroom%", Matcher.quoteReplacement(args[1])));
									}

								} else if (args.length > 2) {
									sender.sendMessage(Lang.NO_CHATROOM_NAME_SPACE.toString());

								} else {

									sender.sendMessage(Lang.CHATROOM_CREATE_HELP.toString());
								}

							} else {
								sender.sendMessage(Lang.ONLY_PLAYER.toString());
							}

							break;
						case "목록":
							if (args.length == 1) {

								ArrayList<ChatRoom> rooms = new ArrayList<ChatRoom>(ChatRoomPlugin.existRooms.values());

								int start = 0;
								int endEx = ChatRoomPlugin.room_per_page;

								if (endEx > rooms.size()) {
									endEx = rooms.size();
								}

								if (start > rooms.size()) {

									sender.sendMessage(Lang.CHATROOM_LIST.toString().replaceAll("%chatroom_list%",
											Lang.EMPTY_LIST.toString()));

								} else {

									StringBuilder list = new StringBuilder();

									for (int i = start; i < endEx; i++) {

										ChatRoom room = rooms.get(i);

										list.append(Lang.withPlaceHolder(Lang.CHATROOM_LIST_LINE,
												new String[] { "%index%", "%chatroom%", "%size%" }, i, room.getName(),
												room.getTeamSize()));
										if (i < endEx - 1) {
											list.append("\n");
										}

									}

									sender.sendMessage(Lang.CHATROOM_LIST.toString().replaceAll("%chatroom_list%",
											list.toString()));

								}
							} else if (args.length == 2) {
								if (MCUtils.isInteger(args[1])) {
									int page = Integer.parseInt(args[1]);
									if (page >= 1) {

										ArrayList<ChatRoom> rooms = new ArrayList<ChatRoom>(
												ChatRoomPlugin.existRooms.values());

										int start = ChatRoomPlugin.room_per_page * (page - 1);
										int endEx = ChatRoomPlugin.room_per_page * page;

										if (endEx > rooms.size()) {
											endEx = rooms.size();
										}

										if (start > rooms.size()) {

											sender.sendMessage(Lang.CHATROOM_LIST.toString()
													.replaceAll("%chatroom_list%", Lang.EMPTY_LIST.toString()));

										} else {

											StringBuilder list = new StringBuilder();

											for (int i = start; i < endEx; i++) {

												ChatRoom room = rooms.get(i);

												list.append(Lang.withPlaceHolder(Lang.CHATROOM_LIST_LINE,
														new String[] { "%index%", "%chatroom%", "%size%" }, i,
														room.getName(), room.getTeamSize()));
												if (i < endEx - 1) {
													list.append("\n");
												}

											}

											sender.sendMessage(Lang.CHATROOM_LIST.toString()
													.replaceAll("%chatroom_list%", list.toString()));

										}

									} else {
										sender.sendMessage(Lang.PAGE_CANNOT_UNDER_1.toString());

									}

								}else {
									sender.sendMessage(Lang.PAGE_SHOULD_NUMBER.toString());
								}

							} else {
								sender.sendMessage(Lang.CHATROOM_LIST_HELP.toString());

							}

							break;

						case "수락":
							if (MCUtils.mustBePlayer(sender)) {
								Player player = (Player) sender;

								if (args.length == 1) {

									MCUtils.acceptInvite(player);

								} else {
									sender.sendMessage(Lang.CHATROOM_ACCEPT_HELP.toString());
								}
							}

							break;

						case "거절":
							if (MCUtils.mustBePlayer(sender)) {
								Player player = (Player) sender;

								if (args.length == 1) {

									MCUtils.denyInvite(player);

								} else {
									sender.sendMessage(Lang.CHATROOM_DENY_HELP.toString());
								}

							}

							break;

						case "초대":

							if (args.length == 2) {

								if (MCUtils.mustBePlayer(sender)) {

									Player player = (Player) sender;

									if (MCUtils.mustBeLeader(player)) {

										if (MCUtils.mustBeOnline(args[1], sender)) {

											MCUtils.inviteAutoExpire(player.getName(), args[1], player);

										}

									}

								}

							} else {
								sender.sendMessage(Lang.INVITE_HELP.toString());

							}

							break;

						case "지도자":

							if (args.length == 2) {

								if (MCUtils.mustBePlayer(sender)) {
									Player p = (Player) sender;
									ChatRoom room = MCUtils.getChatRoom(p);

									if (MCUtils.mustBeLeader(p)) {

										if (MCUtils.mustRoomHasThisP(sender, args[1], room)) {
											if (room.isLeader(args[1])) {
												sender.sendMessage(
														Lang.ALREADY_LEADER.toString().replaceAll("%target%", args[1]));
											} else {

												room.setLeader(args[1]);

												Player target = Bukkit.getPlayerExact(args[1]);

												sender.sendMessage(Lang.NOW_LEADER_TARGET.toString()
														.replaceAll("%target%", args[1]));

												if (target != null) {
													sender.sendMessage(Lang.NOW_LEADER_TO_TARGET.toString());

												}

											}

										}

									}

								}

							} else {
								sender.sendMessage(Lang.CHATROOM_SETLEADER_HELP.toString());
							}

							break;

						case "조회":

							if (args.length == 2) {

								ChatRoom room = MCUtils.getChatRoom(args[1]);

								if (room != null) {

									String name = room.getName();

									sender.sendMessage(Lang.TARGET_LOOKUP_CHATROOM.toString()
											.replaceAll("%target%", args[1]).replaceAll("%chatroom%", name));

								} else {

									sender.sendMessage(
											Lang.TARGET_HAS_NO_CHATROOM.toString().replaceAll("%target%", args[1]));
								}

							} else {
								sender.sendMessage(Lang.LOOKUP_PLAYER_CHATROOM_HELP.toString());
							}

							break;

						case "나가기":

							if (MCUtils.mustBePlayer(sender)) {
								Player player = (Player) sender;

								if (MCUtils.mustHaveRoom(player)) {

									ChatRoom room = MCUtils.getChatRoom(player);

									room.removeLeader(player.getName());

									room.removeMember(player.getName());

									sender.sendMessage(
											Lang.LEAVE_ROOM.toString().replaceAll("%chatroom%", room.getName()));

									room.sendBroadMessage(Lang.LEAVE_MEMBER_TO_ROOM.toString().replaceAll("%player%",
											player.getName()));

									plugin.sendBroadtoSpy(room, Lang.LEAVE_MEMBER_TO_ROOM.toString()
											.replaceAll("%player%", player.getName()));

									if (room.getLeaders().size() < 1) {

										plugin.deleteChatRoom(room);

										MCUtils.broadcast(Lang.LEAVE_MEMBER_DELETE_ROOM.toString()
												.replaceAll("%chatroom%", room.getName()));

									}

								}

							}

							break;

						case "추방":
							if (args.length == 2) {
								if (MCUtils.mustBePlayer(sender)) {
									Player player = (Player) sender;

									if (MCUtils.mustBeLeader(player)) {
										ChatRoom room = MCUtils.getChatRoom(player);
										if (MCUtils.mustRoomHasThisP(sender, args[1], room)) {
											if (!room.isLeader(args[1])
													&& !player.getName().equalsIgnoreCase(args[1])) {

												room.removeMember(args[1]);

												String kicked = Lang.KICKED_PLAYER.toString().replaceAll("%target%",
														args[1]);
												room.sendBroadMessage(kicked);
												MCUtils.sendMsgTo(args[1], Lang.KICKED_FROM_CHATROOM.toString()
														.replaceAll("%chatroom%", room.getName()));
												plugin.sendSpyPartyMessage(room, kicked);

											} else {
												sender.sendMessage(Lang.CANNOT_KICK_ME_OR_LEADER.toString());
											}

										}

									}

								}
							} else {
								sender.sendMessage(Lang.KICK_HELP.toString());
							}

							break;

						default:
							sender.sendMessage(Lang.CHATROOM_CMD_HELP.toString());
							break;
						}
					}
				}.runTaskAsynchronously(ChatRoomPlugin.plugin);

			} else {
				sender.sendMessage(Lang.CHATROOM_CMD_HELP.toString());
				return true;
			}

		} else {
			sender.sendMessage(Lang.NO_PERM.toString());

		}

		return true;
	}
}
