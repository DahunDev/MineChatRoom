package net.daniel.ChatRoom;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import com.google.common.base.CaseFormat;

import net.daniel.ChatRoom.Utils.MCUtils;



public enum Lang {
	// Class 명이 파일명과 같을 필요는 없음
	// 이름이 파일에 있는 메세지와 같을것, 띄어쓰기는 _ 로 대체 (메세지 X)


	MIGRATE_NO_CHATROOM("&b&l[&f채팅방&b&l]&f 이전할려는 %target%님은 소속된 채팅방이 없습니다."),
	ALREADY_HAS_CHATROOM("&b&l[&f채팅방&b&l]&f %target% 님은 이미 소속된 채팅방이 있습니다."),
	ALREADY_HAS_CHATROOM_ME("&b&l[&f채팅방&b&l]&f 당신은 이미 소속된 채팅방이 있습니다."),

	MIGRATED_PLAYER_LEADER("&b&l[&f채팅방&b&l]&f 계정변경 명령어로, %before%님의 채팅방 정보가 %after%님으로 이전되었습니다. (채팅방: %chatroom%, 분류: 지도자)" ),
	MIGRATED_PLAYER_MEMBER("&b&l[&f채팅방&b&l]&f 계정변경 명령어로, %before%님의 채팅방 정보가 %after%님으로 이전되었습니다. (채팅방: %chatroom%, 분류: 멤버)"),

	CHATROOM_MANAGE_HELP("&b&l[&f채팅방&b&l]&f /채팅방관리 생성 (채팅방명) (닉네임) &7: 지도자가 <닉네임>님인 채팅방 (채팅방명)을 생성합니다.\n" +
			"&b&l[&f채팅방&b&l]&f /채팅방관리 계정이전 (기존닉네임) (새닉네임) &7: 채팅방 정보 계정이전\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방관리 해체 (채팅방 이름) &7: 해당 채팅방을 삭제합니다.\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방관리 추가 (채팅방 이름) (닉네임) &7: 채팅방에 멤버추가\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방관리 추방 (채팅방 이름) (닉네임)&7: 채팅방의 멤버추방\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방관리 스파이\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방관리 데이터리로드\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방관리 설정리로드\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방관리 데이터저장\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방관리 지도자 (채팅방 이름) (닉네임) &7: 채팅방 멤버 (닉네임)을 지도자로 변경합니다."),

	CHATROOM_CMD_HELP("&b&l[&f채팅방&b&l]&f /채팅방 생성 <채팅방명>\n" +

			"&b&l[&f채팅방&b&l]&f /채팅방 정보 (채팅방 이름)\n"+

			"&b&l[&f채팅방&b&l]&f /채팅방 목록 [페이지]\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방 수락 &7: 채팅방 초대를 수락합니다.\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방 거절 &7: 채팅방 초대를 거절합니다.\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방 추방 <닉네임> &7: <닉네임>님을 현재 채팅방에서 추방합니다.\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방 초대 <닉네임> &7: <닉네임>님을 본인이 있는 채팅방에 초대합니다.\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방 지도자 <닉네임> &7: <닉네임>님을 지도자로 변경합니다.\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방 나가기 &7: 현재 채팅방에서 퇴장합니다.\n"+
			"&b&l[&f채팅방&b&l]&f /채팅방 조회 <닉네임> &7: <닉네임>님이 소속한 채팅방 이름을 조회합니다.\n" +
			"&b&l[&f채팅방&b&l]&f /chat &7: 채팅방 채팅/일반채팅 모드로 전환합니다.\n" +
			"&b&l[&f채팅방&b&l]&f /g <채팅내용> &7: 채팅방에 대화를 합니다."),


	MIGRATE_PLAYER_HELP("&b&l[&f채팅방&b&l]&f /채팅방계정이전 <기존닉네임> <새닉네임>"),

	CHATROOM_FORCEADD_MEMBER("&b&l[&f채팅방&b&l]&f 관리자에 의해 %target%님이 %chatroom% 채팅방 멤버로 추가됩니다."),
	CHATROOM_FORCEADD_MEMBER_TO_TARGET("&b&l[&f채팅방&b&l]&f 관리자에 의해 %chatroom% 채팅방의 멤버가 되었습니다."),


	CHATROOM_ADMIN_ADD_HELP("&b&l[&f채팅방&b&l]&f /채팅방관리 해체 (채팅방 이름) &7: 해당 채팅방을 삭제합니다."),
	CHATROOM_ADD_ADMIN_HELP("&b&l[&f채팅방&b&l]&f /채팅방관리 추가 (채팅방 이름) (닉네임) &7: 채팅방에 멤버추가"),
	CHATROOM_CREATE_ADMIN_HELP("&b&l[&f채팅방&b&l]&f /채팅방관리 생성 (채팅방명) (닉네임) &7: 지도자가 <닉네임>님인 채팅방 (채팅방명)을 생성합니다."),
	CHATROOM_KICK_ADMIN_HELP("&b&l[&f채팅방&b&l]&f /채팅방관리 추방 (채팅방 이름) (닉네임)&7: 채팅방의 멤버추방"),
	CHATROOM_SETLEADER_ADMIN_HELP("&b&l[&f채팅방&b&l]&f /채팅방관리 지도자 (채팅방 이름) (닉네임) &7: 채팅방 멤버 (닉네임)을 지도자로 변경합니다."),
	CHATROOM_DELETE_ADMIN_HELP("&b&l[&f채팅방&b&l]&f /채팅방관리 해체 (채팅방 이름) &7: 해당 채팅방을 삭제합니다."),
	CHATROOM_DELETE_ADMIN("&b&l[&f채팅방&b&l]&f 채팅방 %chatroom%이(가) 관리자에 의해 해체됐습니다."),


	CHATROOM_SPY_HELP("&b&l[&f채팅방&b&l]&f /채팅방관리 스파이 &7: 채팅방 스파이 모드를 활성화/비활성화 합니다. (본인에게만 적용)"),


	SAVED_DATA("&b&l[&f채팅방&b&l]&f 데이터 저장 완료"),
	RELOADED_CONFIG("&b&l[&f채팅방&b&l] &f설정 재로드 완료"),
	RELOADED_DATA("&b&l[&f채팅방&b&l] &f데이터 리로드 완료"),

	SPY_MODE_ENALBED("&b&l[&f채팅방&b&l] &f스파이 모드가 활성화 됐습니다."),
	SPY_MODE_DISABLED("&b&l[&f채팅방&b&l] &f스파이 모드가 비활성화 됐습니다."),


	CHATROOM_TOGGLED("&b&l[&f채팅방&b&l]&f 채팅방 모드가 활성화 됐습니다. /chat 입력시 다시 일반채팅모드로 전환됩니다."),

	GLOBAL_CHAT_TOGGLED("&b&l[&f채팅방&b&l]&f 일반채팅 모드로 복구 됐습니다. /chat 입력시 다시 채팅방모드로 전환됩니다."),






	CHATROOM_FORCEREMOVE_MEMBER("&b&l[&f채팅방&b&l]&f 관리자에 의해 %target%님이 %chatroom% 채팅방에서 추방되었습니다."),
	CHATROOM_FORCEREMOVE_MEMBER_TO_TARGET("&b&l[&f채팅방&b&l]&f 관리자에 의해 %chatroom% 채팅방에서 추방되었습니다."),
	NOT_MEMBER_TARGET("&b&l[&f채팅방&b&l]&f %target%님은 %chatroom% 채팅방에 소속되어 있지 않습니다."),
	NOT_MEMBER_MYSELF("&b&l[&f채팅방&b&l]&f 당신은 그 채팅방에 소속되어 있지 않습니다."),
	CHATROOM_ADMIN_REMOVE_MEMBER_HELP("&b&l[&f채팅방&b&l]&f /채팅방관리 삭제 <채팅방이름> <채팅방원이름>"),

	MY_CHATROOM_SIMPLE("&a&b&l[&f채팅방&b&l]&f 당신의 채팅방은 %chatroom% 입니다. 상세 보기를 하실려면 /채팅방 정보 <채팅방이름> 을 치시길 바랍니다."),


	CHATROOM_LIST_HELP("&b&l[&f채팅방&b&l]&f /채팅방 목록 [페이지]"),
	CHATROOM_CREATE_HELP("&b&l[&f채팅방&b&l]&f /채팅방 생성 <채팅방명>" ),
	CHATROOM_LEAVE_HELP("&b&l[&f채팅방&b&l]&f /채팅방 나가기" ),

	CHATROOM_SETLEADER_HELP("&b&l[&f채팅방&b&l]&f /채팅방 지도자 <닉네임>" ),

	CHATROOM_LOOKUP_HELP("&b&l[&f채팅방&b&l]&f /채팅방 조회 <닉네임>" ),
	CHATROOM_INFO_HELP("&b&l[&f채팅방&b&l]&f /채팅방 정보 [채팅방 이름]" ),
	LOOKUP_PLAYER_CHATROOM_HELP("&b&l[&f채팅방&b&l]&f /채팅방 조회 <닉네임>"),
	CHATROOM_ACCEPT_HELP("&b&l[&f채팅방&b&l]&f /채팅방 수락 &7: 채팅방 초대를 수락합니다." ),
	CHATROOM_DENY_HELP("&b&l[&f채팅방&b&l]&f /채팅방 거절 &7: 채팅방 초대를 거절합니다." ),


	PAGE_CANNOT_UNDER_1("&b&l[&f채팅방&b&l] &c페이지 숫자는 1보다 작을 수 없습니다."),
	PAGE_SHOULD_NUMBER("&b&l[&f채팅방&b&l] &c페이지 숫자는 양의 정수여야 합니다."),


	JOIN_CHATROOM("&b&l[&f채팅방&b&l] &f%player%님이 이 채팅방에 입장하셨습니다."),

	CHATROOM_LIST("&b&l[&f채팅방&b&l]&f ==============================\n"+
			"%chatroom_list%"
	),
	CHATROOM_LIST_LINE("&b[%index%] &f%chatroom% &b인원: &f%size%"),

	CHATROOM_INFO("&b&l[&f채팅방&b&l]&f ================= &b정보 &f=================\n"
			+ "&b&l[&f채팅방&b&l] &9이름: &f%chatroom%\n"
			+ "&b&l[&f채팅방&b&l] &4지도자: &f%leaders%\n"
			+ "&b&l[&f채팅방&b&l] &e멤버: &f%members%\n"
			+ "&b&l[&f채팅방&b&l] &a인원: &f%size%\n"
	),

	CHATROOM_INFO_MINE("&b&l[&f채팅방&b&l]&f ================= &b당신이 속한 채팅방 정보 &f=================\n"
			+ "&b&l[&f채팅방&b&l] &9이름: &f%chatroom%\n"
			+ "&b&l[&f채팅방&b&l] &4지도자: &f%leaders%\n"
			+ "&b&l[&f채팅방&b&l] &e멤버: &f%members%\n"
			+ "&b&l[&f채팅방&b&l] &a인원: &f%size%\n"
	),

	CHATROOM_NOTEXIST("&b&l[&f채팅방&b&l] &f%chatroom% 채팅방은 존재 하지 않습니다."),
	CHATROOM_ALREADY_EXIST("&b&l[&f채팅방&b&l] &f%chatroom% 채팅방은 이미 존재합니다."),


	NO_PERM("&b&l[ &f&lServer &b&l] &c권한이 없습니다."),


	ONLY_LEADER("&b&l[&f채팅방&b&l] &f해당작업은 지도자만 가능합니다."),

	ONLY_PLAYER("&b&l[&f채팅방&b&l] &f해당작업은 접속중인 플레이어만 가능합니다."),

	PLAYER_HAS_NO_CHATROOM("&b&l[&f채팅방&b&l]&f 당신은 소속된 채팅방이 없습니다."),

	CREATED_CHATROOM("&b&l[&f채팅방&b&l] &f채팅방 %chatroom% 이(가) 생성되었습니다."),
	CREATED_CHATROOM_BY_ADMIN("&b&l[&f채팅방&b&l] &f관리자에 의해 지도자가 %target%님으로 설정된 채팅방 %chatroom%이 생성되었습니다."),


	TARGET_HAS_NO_CHATROOM("&b&l[&f채팅방&b&l]&f %target%님은 소속된 채팅방이 없습니다."),
	TARGET_LOOKUP_CHATROOM("&b&l[&f채팅방&b&l]&f %target%님은 %chatroom% 채팅방에 소속되어 있습니다."),
	PLAYER_NOT_ONLINE("&b&l[&f채팅방&b&l]&f &c%target%님은 온라인이 아닙니다."),

	LEAVE_ROOM("&b&l[&f채팅방&b&l]&f %chatroom% 채팅방에서 나갔습니다."),
	LEAVE_MEMBER_TO_ROOM("&b&l[&f채팅방&b&l]&f %player%님이 채팅방에서 퇴장하였습니다."),

	LEAVE_MEMBER_DELETE_ROOM("&b&l[&f채팅방&b&l] &f%chatroom%의 지도자가 모두 퇴장하여, 해당 채팅방이 해체됐습니다."),


	CHATROOM_MSG_CMD_HELP("&b&l[&f채팅방&b&l]&f /g <채팅내용> &7: 채팅방에 대화를 합니다."),
	NOT_HAVE_CHATROOM("&b&l[&f채팅방&b&l]&f 당신은 소속된 채팅방이 없습니다. 채팅방에 가입하거나, /채팅방 생성 <채팅방이름> 으로 채팅방을 생성하시면 채팅방 채팅이 가능합니다."),


	INVITED_PLAYER("&b&l[&f채팅방&b&l]&f &a%target%&f님에게 채팅방 초대신청을 보냈습니다.."),

	NOT_INVITED("&b&l[&f채팅방&b&l] &c현재 수락/거절할 초대가 없습니다."),


	INVITE_HELP("&b&l[&f채팅방&b&l]&f /채팅방 초대 <닉네임> &7: <닉네임>님을 본인이 있는 채팅방에 초대합니다."),

	INVITE_DENIED_BY_EXPIRED_TARGET("&b&l[&f채팅방&b&l]&f초대 수락여부가 시간초과로 &c자동거절&f되었습니다."),

	INVITE_DENIED_BY_EXPIRED("&b&l[&f채팅방&b&l]&f %target%님에 대한 초대가 시간초과로 &c거절&f되었습니다."),

	INVITE_REQUESTED_BY_OTHER("&b&l[&f채팅방&b&l]&f %target%님에 대한 초대는 이미 다른 플레이어가 초대한 상태이면서 해당 초대가 만료되어 있지 않아 초대가 취소되었습니다. (현재 해당 플레이러를 초대한 사람: %other%)"),


	INVITED_INFO_TO_TARGET("&b&l[&f채팅방&b&l]&f 채팅방 %chatroom%(으)로 부터 초대가 왔습니다. %sec%초후 자동으로 거절됩니다.\n" +
			"&b&l[&f채팅방&b&l]&f 초대를 수락 하시려면, &a/채팅방 수락&f, 거절하시려면 &e/채팅방 거절 &f명령어를 입력하세요."),

	ACCEPTED_INVITE_TARGET("&b&l[&f채팅방&b&l] &f초대를 승락했습니다. 당신은 이제 해당 채팅방의 멤버입니다."),
	ACCEPT_INVITE_INVITER("&b&l[&f채팅방&b&l] &f%target%님이 초대를 승락했습니다. 이제 %target%님은 같은 채팅방의 멤버입니다."),


	DENIED_INVITE_TARGET("&b&l[&f채팅방&b&l] &f초대를 거절했습니다."),
	DENIED_INVITE_INVITER("&b&l[&f채팅방&b&l] &f%target%님이 초대를 거절했습니다."),



	ALREADY_INVITED("&b&l[&f채팅방&b&l]&f &c이미 %target%님을 초대 했습니다."),
	ALREADY_LEADER("&b&l[&f채팅방&b&l]&f &c%target%님은 이미 지도자 입니다."),

	SET_LEADER_ADMIN("&b&l[&f채팅방&b&l]&f 관리자에 의해 %target%님은 이제 %chatroom% 채팅방의 지도자 입니다."),

	NOW_LEADER_TARGET("&b&l[&f채팅방&b&l]&f &c%target%님은 이제 지도자 입니다."),

	NOW_LEADER_TO_TARGET("&b&l[&f채팅방&b&l]&f 당신은 이제 지도자 입니다."),

	CANNOT_INVITE_NO_PERM("&b&l[&f채팅방&b&l]&f &c채팅방 초대는 지도자만 가능합니다."),
	CANNOT_KICK_ME_OR_LEADER("&b&l[&f채팅방&b&l]&f &c자신 또는 지도자는 추방을 할 수 없습니다.!"),
	NOT_SAME_CHATROOM_MEMBER("&b&l[&f채팅방&b&l]&f &c%target%님은 같은 채팅방 회원이 아닙니다!"),
	NO_CHATROOM_NAME_SPACE("&b&l[&f채팅방&b&l]&f &c채팅방 이름은 띄어쓰기 없이 해야 합니다."),

	CONTAINS_NOALLOWED_CHAR("&b&l[&f채팅방&b&l]&f &c허용되지 않은 문자가 포함되어 있습니다. (금지 문자: 색코드,\".\",\"#\",\"$\" )"),


	KICK_HELP("&b&l[&f채팅방&b&l]&f /채팅방 추방 <닉네임> &7: <닉네임>님을 현재 채팅방에서 추방합니다."),

	KICKED_PLAYER("&b&l[&f채팅방&b&l]&f &c%target%님을 채팅방에서 추방하였습니다.."),
	KICKED_FROM_CHATROOM("&b&l[&f채팅방&b&l]&f &c당신은 %chatroom% 채팅방에서 추방 당하였습니다.."),

	EMPTY_LIST("없음"),

	;

	private final String def;

	Lang(String def) {
		this.def = def;
	}

	public static void init(ConfigurationSection section) {
		for (Lang lang : Lang.values()) {

			String key = lang.key();
			if (!section.contains(key)) {
				section.set(key, lang.def);
			}
		}
	}


	public static String withPlaceHolder(Lang lang, String[] placeholders, Object... values) {

		StringBuffer msg = new StringBuffer(lang.toString());
		for (int i = 0; i < placeholders.length; i++) {
			MCUtils.replaceAll(msg, placeholders[i], String.valueOf(values[i]));
		}

		return msg.toString();

	}


	public String toString(ConfigurationSection lang) {
		return ChatColor.translateAlternateColorCodes('&', lang.getString(key(), def));
	}

	@Override
	public String toString() {
		return toString(ChatRoomPlugin.plugin.getLangConfig());
	}

	private String key() {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
	}

}