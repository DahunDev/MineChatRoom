package net.daniel.ChatRoom.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

public class ManageCommand implements CommandExecutor, TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> list = new ArrayList<>();

		if (args.length < 2) {

			if (sender.hasPermission("ChatRoom.Admin")) {
				list.add("생성");
				list.add("계정이전");

				list.add("해체");
				list.add("추가");

				list.add("추방");

				list.add("스파이");

				list.add("데이터리로드");

				list.add("설정리로드");

				list.add("데이터저장");

				list.add("지도자");
			}

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

		if (sender.hasPermission("ChatRoom.Admin")) {

			if (args.length >= 1) {

				new BukkitRunnable() {

					@Override
					public void run() {
						switch (args[0]) {
							case "생성":

								if (args.length == 3) {

									String name = ChatColor.translateAlternateColorCodes('&', args[1]);

									int size = ChatColor.stripColor(name).length();

									if (size == args[1].length()) {

										if (!((args[1].contains(".") || args[1].contains(",") || args[1].contains("#")))) {

											if (ChatRoomPlugin.existRooms.get(name) == null) {
												if (ChatRoomPlugin.playerChatParties.get(args[2].toLowerCase()) == null) {

													ChatRoomPlugin.createChatRoom(args[1], args[2].toLowerCase());

													MCUtils.broadcast(Lang.CREATED_CHATROOM_BY_ADMIN.toString()
															.replaceAll("%target%", args[2])
															.replaceAll("%chatroom%", args[1].toLowerCase()));

												} else {
													sender.sendMessage(Lang.ALREADY_HAS_CHATROOM.toString()
															.replaceAll("%target%", args[2]));
												}

											} else {
												sender.sendMessage(Lang.CHATROOM_ALREADY_EXIST.toString()
														.replaceAll("%chatroom%", name));
											}

										} else {
											sender.sendMessage(Lang.CONTAINS_NOALLOWED_CHAR.toString());
										}

									} else {
										sender.sendMessage(Lang.CONTAINS_NOALLOWED_CHAR.toString());

									}

								} else if (args.length >= 4) {

									sender.sendMessage(Lang.NO_CHATROOM_NAME_SPACE.toString());
								} else {
									sender.sendMessage(Lang.CHATROOM_CREATE_ADMIN_HELP.toString());
								}

								break;

							case "계정이전":

								if (args.length == 3) {
									ChatRoom room = MCUtils.getChatRoom(args[1]);
									if (room != null) {
										if (ChatRoomPlugin.playerChatParties.get(args[2].toLowerCase()) == null) {

											boolean isLeader, isMember;

											isLeader = room.isLeader(args[1]);
											isMember = room.isMember(args[1]);

											if (isLeader) {
												room.removeLeader(args[1]);
												room.addLeader(args[2]);

												if (isMember) {
													room.removeMember(args[1]);
												}

												sender.sendMessage(Lang.withPlaceHolder(Lang.MIGRATED_PLAYER_LEADER,
														new String[] { "%before%", "%after%", "%chatroom%" }, args[1],
														args[2], room.getName()));

											} else {
												room.removeMember(args[1]);
												room.addMember(args[2]);
												sender.sendMessage(Lang.withPlaceHolder(Lang.MIGRATED_PLAYER_MEMBER,
														new String[] { "%before%", "%after%", "%chatroom%" }, args[1],
														args[2], room.getName()));
											}

										} else {
											sender.sendMessage(
													Lang.ALREADY_HAS_CHATROOM.toString().replaceAll("%target%", args[2]));

										}

									} else {
										sender.sendMessage(
												Lang.MIGRATE_NO_CHATROOM.toString().replaceAll("%target%", args[1]));
									}

								} else {
									sender.sendMessage(Lang.MIGRATE_PLAYER_HELP.toString());
								}

								break;

							case "해체":
								if (args.length == 2) {
									ChatRoom room = ChatRoomPlugin.existRooms.get(args[1].toLowerCase());
									if (room != null) {

										ChatRoomPlugin.plugin.deleteChatRoom(room);

										MCUtils.broadcast(
												Lang.CHATROOM_DELETE_ADMIN.toString().replaceAll("%chatroom%", args[1]));

									} else {
										sender.sendMessage(
												Lang.CHATROOM_NOTEXIST.toString().replaceAll("%chatroom%", args[1]));
									}

								} else {
									sender.sendMessage(Lang.CHATROOM_DELETE_ADMIN_HELP.toString());

								}

								break;

							case "추가":
								if (args.length == 3) {
									ChatRoom room = ChatRoomPlugin.existRooms.get(args[1].toLowerCase());
									if (room != null) {
										if (ChatRoomPlugin.playerChatParties.get(args[2].toLowerCase()) == null) {

											room.addMember(args[2]);
											MCUtils.sendMsgTo(args[2], Lang.CHATROOM_FORCEADD_MEMBER_TO_TARGET.toString()
													.replaceAll("%chatroom%", args[1]).replaceAll("%target%", args[2]));
											MCUtils.broadcast(Lang.CHATROOM_FORCEADD_MEMBER.toString()
													.replaceAll("%chatroom%", args[1]).replaceAll("%target%", args[2]));

										} else {
											sender.sendMessage(
													Lang.ALREADY_HAS_CHATROOM.toString().replaceAll("%target%", args[2]));
										}

									} else {
										sender.sendMessage(
												Lang.CHATROOM_NOTEXIST.toString().replaceAll("%chatroom%", args[1]));
									}

								} else {
									sender.sendMessage(Lang.CHATROOM_ADD_ADMIN_HELP.toString());
								}
								break;

							case "추방":
								if (args.length == 3) {
									ChatRoom room = ChatRoomPlugin.existRooms.get(args[1].toLowerCase());
									if (room != null) {

										if (MCUtils.mustRoomHasThisP(sender, args[2], room)) {

											room.removeMember(args[2]);
											room.removeLeader(args[2]);

											MCUtils.sendMsgTo(args[2], Lang.CHATROOM_FORCEREMOVE_MEMBER_TO_TARGET.toString()
													.replaceAll("%chatroom%", args[1]));
											MCUtils.broadcast(Lang.CHATROOM_FORCEREMOVE_MEMBER.toString()
													.replaceAll("%chatroom%", args[1]).replaceAll("%target%", args[2]));


											if(room.getLeaders().size() < 1 ) {
												ChatRoomPlugin.plugin.deleteChatRoom(room);

												MCUtils.broadcast(Lang.LEAVE_MEMBER_DELETE_ROOM.toString()
														.replaceAll("%chatroom%", room.getName()));
											}


										} else {
											sender.sendMessage(Lang.NOT_MEMBER_TARGET.toString()
													.replaceAll("%target%", args[2]).replaceAll("%chatroom%", args[1]));
										}

									} else {
										sender.sendMessage(
												Lang.CHATROOM_NOTEXIST.toString().replaceAll("%chatroom%", args[1]));
									}

								} else {
									sender.sendMessage(Lang.CHATROOM_KICK_ADMIN_HELP.toString());
								}
								break;

							case "스파이":
								if (MCUtils.mustBePlayer(sender)) {
									Player player = (Player) sender;
									if (MCUtils.toggleSpy(player)) {
										sender.sendMessage(Lang.SPY_MODE_ENALBED.toString());

									} else {

										sender.sendMessage(Lang.SPY_MODE_DISABLED.toString());

									}

								}

								break;
							case "지도자":
								if (args.length == 3) {
									ChatRoom room = ChatRoomPlugin.existRooms.get(args[1].toLowerCase());
									if (room != null) {
										if (MCUtils.mustRoomHasThisP(sender, args[2], room)) {
											if (!room.isLeader(args[2])) {

												room.setLeader(args[2]);

												MCUtils.broadcast(Lang.SET_LEADER_ADMIN.toString()
														.replaceAll("%target%", args[2]).replaceAll("%chatroom%", args[1]));

											} else {
												sender.sendMessage(
														Lang.ALREADY_LEADER.toString().replaceAll("%target%", args[2]));
											}

										}

									} else {
										sender.sendMessage(
												Lang.CHATROOM_NOTEXIST.toString().replaceAll("%chatroom%", args[1]));
									}

								} else {
									sender.sendMessage(Lang.CHATROOM_SETLEADER_ADMIN_HELP.toString());
								}
								break;

							case "데이터리로드":

								ChatRoomPlugin.plugin.loadChatRoomData();

								sender.sendMessage(Lang.RELOADED_DATA.toString());

								break;

							case "설정리로드":

								ChatRoomPlugin.plugin.reloadConfiguration();
								sender.sendMessage(Lang.RELOADED_CONFIG.toString());
								break;

							case "데이터저장":

								ChatRoomPlugin.ChatRoomYML.saveData();

								sender.sendMessage(Lang.SAVED_DATA.toString());

								break;

							default:
								break;
						}

					}
				}.runTaskAsynchronously(ChatRoomPlugin.plugin);

			} else {
				sender.sendMessage(Lang.CHATROOM_MANAGE_HELP.toString());
			}

		} else {
			sender.sendMessage(Lang.NO_PERM.toString());
		}

		return true;
	}

}