package net.daniel.ChatRoom;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import com.google.common.base.CaseFormat;

import net.daniel.ChatRoom.Utils.MCUtils;



public enum Lang {
	// Class ���� ���ϸ�� ���� �ʿ�� ����
	// �̸��� ���Ͽ� �ִ� �޼����� ������, ����� _ �� ��ü (�޼��� X)


	MIGRATE_NO_CHATROOM("&b&l[&fä�ù�&b&l]&f �����ҷ��� %target%���� �Ҽӵ� ä�ù��� �����ϴ�."),
	ALREADY_HAS_CHATROOM("&b&l[&fä�ù�&b&l]&f %target% ���� �̹� �Ҽӵ� ä�ù��� �ֽ��ϴ�."),
	ALREADY_HAS_CHATROOM_ME("&b&l[&fä�ù�&b&l]&f ����� �̹� �Ҽӵ� ä�ù��� �ֽ��ϴ�."),

	MIGRATED_PLAYER_LEADER("&b&l[&fä�ù�&b&l]&f �������� ��ɾ��, %before%���� ä�ù� ������ %after%������ �����Ǿ����ϴ�. (ä�ù�: %chatroom%, �з�: ������)" ),	
	MIGRATED_PLAYER_MEMBER("&b&l[&fä�ù�&b&l]&f �������� ��ɾ��, %before%���� ä�ù� ������ %after%������ �����Ǿ����ϴ�. (ä�ù�: %chatroom%, �з�: ���)"),
	
	CHATROOM_MANAGE_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù���� ���� (ä�ù��) (�г���) &7: �����ڰ� <�г���>���� ä�ù� (ä�ù��)�� �����մϴ�.\n" +
	"&b&l[&fä�ù�&b&l]&f /ä�ù���� �������� (�����г���) (���г���) &7: ä�ù� ���� ��������\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù���� ��ü (ä�ù� �̸�) &7: �ش� ä�ù��� �����մϴ�.\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù���� �߰� (ä�ù� �̸�) (�г���) &7: ä�ù濡 ����߰�\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù���� �߹� (ä�ù� �̸�) (�г���)&7: ä�ù��� ����߹�\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù���� ������\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù���� �����͸��ε�\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù���� �������ε�\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù���� ����������\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù���� ������ (ä�ù� �̸�) (�г���) &7: ä�ù� ��� (�г���)�� �����ڷ� �����մϴ�."),
	
	CHATROOM_CMD_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù� ���� <ä�ù��>\n" +
			
	"&b&l[&fä�ù�&b&l]&f /ä�ù� ���� (ä�ù� �̸�)\n"+

	"&b&l[&fä�ù�&b&l]&f /ä�ù� ��� [������]\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù� ���� &7: ä�ù� �ʴ븦 �����մϴ�.\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù� ���� &7: ä�ù� �ʴ븦 �����մϴ�.\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù� �߹� <�г���> &7: <�г���>���� ���� ä�ù濡�� �߹��մϴ�.\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù� �ʴ� <�г���> &7: <�г���>���� ������ �ִ� ä�ù濡 �ʴ��մϴ�.\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù� ������ <�г���> &7: <�г���>���� �����ڷ� �����մϴ�.\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù� ������ &7: ���� ä�ù濡�� �����մϴ�.\n"+
	"&b&l[&fä�ù�&b&l]&f /ä�ù� ��ȸ <�г���> &7: <�г���>���� �Ҽ��� ä�ù� �̸��� ��ȸ�մϴ�.\n" +
	"&b&l[&fä�ù�&b&l]&f /chat &7: ä�ù� ä��/�Ϲ�ä�� ���� ��ȯ�մϴ�.\n" +
	"&b&l[&fä�ù�&b&l]&f /g <ä�ó���> &7: ä�ù濡 ��ȭ�� �մϴ�."),
	
	
	MIGRATE_PLAYER_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù�������� <�����г���> <���г���>"),

	CHATROOM_FORCEADD_MEMBER("&b&l[&fä�ù�&b&l]&f �����ڿ� ���� %target%���� %chatroom% ä�ù� ����� �߰��˴ϴ�."),
	CHATROOM_FORCEADD_MEMBER_TO_TARGET("&b&l[&fä�ù�&b&l]&f �����ڿ� ���� %chatroom% ä�ù��� ����� �Ǿ����ϴ�."),
	
	
	CHATROOM_ADMIN_ADD_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù���� ��ü (ä�ù� �̸�) &7: �ش� ä�ù��� �����մϴ�."),
	CHATROOM_ADD_ADMIN_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù���� �߰� (ä�ù� �̸�) (�г���) &7: ä�ù濡 ����߰�"),
	CHATROOM_CREATE_ADMIN_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù���� ���� (ä�ù��) (�г���) &7: �����ڰ� <�г���>���� ä�ù� (ä�ù��)�� �����մϴ�."),
	CHATROOM_KICK_ADMIN_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù���� �߹� (ä�ù� �̸�) (�г���)&7: ä�ù��� ����߹�"),
	CHATROOM_SETLEADER_ADMIN_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù���� ������ (ä�ù� �̸�) (�г���) &7: ä�ù� ��� (�г���)�� �����ڷ� �����մϴ�."),
	CHATROOM_DELETE_ADMIN_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù���� ��ü (ä�ù� �̸�) &7: �ش� ä�ù��� �����մϴ�."),
	CHATROOM_DELETE_ADMIN("&b&l[&fä�ù�&b&l]&f ä�ù� %chatroom%��(��) �����ڿ� ���� ��ü�ƽ��ϴ�."),


	CHATROOM_SPY_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù���� ������ &7: ä�ù� ������ ��带 Ȱ��ȭ/��Ȱ��ȭ �մϴ�. (���ο��Ը� ����)"),

	
	SAVED_DATA("&b&l[&fä�ù�&b&l]&f ������ ���� �Ϸ�"),
	RELOADED_CONFIG("&b&l[&fä�ù�&b&l] &f���� ��ε� �Ϸ�"),
	RELOADED_DATA("&b&l[&fä�ù�&b&l] &f������ ���ε� �Ϸ�"),
	
	SPY_MODE_ENALBED("&b&l[&fä�ù�&b&l] &f������ ��尡 Ȱ��ȭ �ƽ��ϴ�."),
	SPY_MODE_DISABLED("&b&l[&fä�ù�&b&l] &f������ ��尡 ��Ȱ��ȭ �ƽ��ϴ�."),
	
	
	CHATROOM_TOGGLED("&b&l[&fä�ù�&b&l]&f ä�ù� ��尡 Ȱ��ȭ �ƽ��ϴ�. /chat �Է½� �ٽ� �Ϲ�ä�ø��� ��ȯ�˴ϴ�."),
	
	GLOBAL_CHAT_TOGGLED("&b&l[&fä�ù�&b&l]&f �Ϲ�ä�� ���� ���� �ƽ��ϴ�. /chat �Է½� �ٽ� ä�ù���� ��ȯ�˴ϴ�."),
	
	
	
	
	
	
	CHATROOM_FORCEREMOVE_MEMBER("&b&l[&fä�ù�&b&l]&f �����ڿ� ���� %target%���� %chatroom% ä�ù濡�� �߹�Ǿ����ϴ�."),
	CHATROOM_FORCEREMOVE_MEMBER_TO_TARGET("&b&l[&fä�ù�&b&l]&f �����ڿ� ���� %chatroom% ä�ù濡�� �߹�Ǿ����ϴ�."),
	NOT_MEMBER_TARGET("&b&l[&fä�ù�&b&l]&f %target%���� %chatroom% ä�ù濡 �ҼӵǾ� ���� �ʽ��ϴ�."),
	NOT_MEMBER_MYSELF("&b&l[&fä�ù�&b&l]&f ����� �� ä�ù濡 �ҼӵǾ� ���� �ʽ��ϴ�."),
	CHATROOM_ADMIN_REMOVE_MEMBER_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù���� ���� <ä�ù��̸�> <ä�ù���̸�>"),
	
	MY_CHATROOM_SIMPLE("&a&b&l[&fä�ù�&b&l]&f ����� ä�ù��� %chatroom% �Դϴ�. �� ���⸦ �ϽǷ��� /ä�ù� ���� <ä�ù��̸�> �� ġ�ñ� �ٶ��ϴ�."),
	
	
	CHATROOM_LIST_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù� ��� [������]"),
	CHATROOM_CREATE_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù� ���� <ä�ù��>" ),
	CHATROOM_LEAVE_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù� ������" ),
	
	CHATROOM_SETLEADER_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù� ������ <�г���>" ),

	CHATROOM_LOOKUP_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù� ��ȸ <�г���>" ),
	CHATROOM_INFO_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù� ���� [ä�ù� �̸�]" ),
	LOOKUP_PLAYER_CHATROOM_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù� ��ȸ <�г���>"),
	CHATROOM_ACCEPT_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù� ���� &7: ä�ù� �ʴ븦 �����մϴ�." ),
	CHATROOM_DENY_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù� ���� &7: ä�ù� �ʴ븦 �����մϴ�." ),


	PAGE_CANNOT_UNDER_1("&b&l[&fä�ù�&b&l] &c������ ���ڴ� 1���� ���� �� �����ϴ�."),
	PAGE_SHOULD_NUMBER("&b&l[&fä�ù�&b&l] &c������ ���ڴ� ���� �������� �մϴ�."),

	
	JOIN_CHATROOM("&b&l[&fä�ù�&b&l] &f%player%���� �� ä�ù濡 �����ϼ̽��ϴ�."),
	
	CHATROOM_LIST("&b&l[&fä�ù�&b&l]&f ==============================\n"+
	"%chatroom_list%"
			),
	CHATROOM_LIST_LINE("&b[%index%] &f%chatroom% &b�ο�: &f%size%"),
	
	CHATROOM_INFO("&b&l[&fä�ù�&b&l]&f ================= &b���� &f=================\n"
			+ "&b&l[&fä�ù�&b&l] &9�̸�: &f%chatroom%\n"
			+ "&b&l[&fä�ù�&b&l] &4������: &f%leaders%\n"
			+ "&b&l[&fä�ù�&b&l] &e���: &f%members%\n"
			+ "&b&l[&fä�ù�&b&l] &a�ο�: &f%size%\n"
			),
	
	CHATROOM_INFO_MINE("&b&l[&fä�ù�&b&l]&f ================= &b����� ���� ä�ù� ���� &f=================\n"
			+ "&b&l[&fä�ù�&b&l] &9�̸�: &f%chatroom%\n"
			+ "&b&l[&fä�ù�&b&l] &4������: &f%leaders%\n"
			+ "&b&l[&fä�ù�&b&l] &e���: &f%members%\n"
			+ "&b&l[&fä�ù�&b&l] &a�ο�: &f%size%\n"
			),
	
	CHATROOM_NOTEXIST("&b&l[&fä�ù�&b&l] &f%chatroom% ä�ù��� ���� ���� �ʽ��ϴ�."),
	CHATROOM_ALREADY_EXIST("&b&l[&fä�ù�&b&l] &f%chatroom% ä�ù��� �̹� �����մϴ�."),

	
	NO_PERM("&b&l[ &f&lServer &b&l] &c������ �����ϴ�."),

	
	ONLY_LEADER("&b&l[&fä�ù�&b&l] &f�ش��۾��� �����ڸ� �����մϴ�."),
	
	ONLY_PLAYER("&b&l[&fä�ù�&b&l] &f�ش��۾��� �������� �÷��̾ �����մϴ�."),
	
	PLAYER_HAS_NO_CHATROOM("&b&l[&fä�ù�&b&l]&f ����� �Ҽӵ� ä�ù��� �����ϴ�."),

	CREATED_CHATROOM("&b&l[&fä�ù�&b&l] &fä�ù� %chatroom% ��(��) �����Ǿ����ϴ�."),
	CREATED_CHATROOM_BY_ADMIN("&b&l[&fä�ù�&b&l] &f�����ڿ� ���� �����ڰ� %target%������ ������ ä�ù� %chatroom%�� �����Ǿ����ϴ�."),

	
	TARGET_HAS_NO_CHATROOM("&b&l[&fä�ù�&b&l]&f %target%���� �Ҽӵ� ä�ù��� �����ϴ�."),
	TARGET_LOOKUP_CHATROOM("&b&l[&fä�ù�&b&l]&f %target%���� %chatroom% ä�ù濡 �ҼӵǾ� �ֽ��ϴ�."),
	PLAYER_NOT_ONLINE("&b&l[&fä�ù�&b&l]&f &c%target%���� �¶����� �ƴմϴ�."),
	
	LEAVE_ROOM("&b&l[&fä�ù�&b&l]&f %chatroom% ä�ù濡�� �������ϴ�."),
	LEAVE_MEMBER_TO_ROOM("&b&l[&fä�ù�&b&l]&f %player%���� ä�ù濡�� �����Ͽ����ϴ�."),
	
	LEAVE_MEMBER_DELETE_ROOM("&b&l[&fä�ù�&b&l] &f%chatroom%�� �����ڰ� ��� �����Ͽ�, �ش� ä�ù��� ��ü�ƽ��ϴ�."),
	
	
	CHATROOM_MSG_CMD_HELP("&b&l[&fä�ù�&b&l]&f /g <ä�ó���> &7: ä�ù濡 ��ȭ�� �մϴ�."),
	NOT_HAVE_CHATROOM("&b&l[&fä�ù�&b&l]&f ����� �Ҽӵ� ä�ù��� �����ϴ�. ä�ù濡 �����ϰų�, /ä�ù� ���� <ä�ù��̸�> ���� ä�ù��� �����Ͻø� ä�ù� ä���� �����մϴ�."),

	
	INVITED_PLAYER("&b&l[&fä�ù�&b&l]&f &a%target%&f�Կ��� ä�ù� �ʴ��û�� ���½��ϴ�.."),
	
	NOT_INVITED("&b&l[&fä�ù�&b&l] &c���� ����/������ �ʴ밡 �����ϴ�."),
	
	
	INVITE_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù� �ʴ� <�г���> &7: <�г���>���� ������ �ִ� ä�ù濡 �ʴ��մϴ�."),
	
	INVITE_DENIED_BY_EXPIRED_TARGET("&b&l[&fä�ù�&b&l]&f�ʴ� �������ΰ� �ð��ʰ��� &c�ڵ�����&f�Ǿ����ϴ�."),

	INVITE_DENIED_BY_EXPIRED("&b&l[&fä�ù�&b&l]&f %target%�Կ� ���� �ʴ밡 �ð��ʰ��� &c����&f�Ǿ����ϴ�."),
	
	INVITE_REQUESTED_BY_OTHER("&b&l[&fä�ù�&b&l]&f %target%�Կ� ���� �ʴ�� �̹� �ٸ� �÷��̾ �ʴ��� �����̸鼭 �ش� �ʴ밡 ����Ǿ� ���� �ʾ� �ʴ밡 ��ҵǾ����ϴ�. (���� �ش� �÷��̷��� �ʴ��� ���: %other%)"),


	INVITED_INFO_TO_TARGET("&b&l[&fä�ù�&b&l]&f ä�ù� %chatroom%(��)�� ���� �ʴ밡 �Խ��ϴ�. %sec%���� �ڵ����� �����˴ϴ�.\n" + 
	"&b&l[&fä�ù�&b&l]&f �ʴ븦 ���� �Ͻ÷���, &a/ä�ù� ����&f, �����Ͻ÷��� &e/ä�ù� ���� &f��ɾ �Է��ϼ���."),
			
	ACCEPTED_INVITE_TARGET("&b&l[&fä�ù�&b&l] &f�ʴ븦 �¶��߽��ϴ�. ����� ���� �ش� ä�ù��� ����Դϴ�."),
	ACCEPT_INVITE_INVITER("&b&l[&fä�ù�&b&l] &f%target%���� �ʴ븦 �¶��߽��ϴ�. ���� %target%���� ���� ä�ù��� ����Դϴ�."),
	
	
	DENIED_INVITE_TARGET("&b&l[&fä�ù�&b&l] &f�ʴ븦 �����߽��ϴ�."),
	DENIED_INVITE_INVITER("&b&l[&fä�ù�&b&l] &f%target%���� �ʴ븦 �����߽��ϴ�."),
	
	
	
	ALREADY_INVITED("&b&l[&fä�ù�&b&l]&f &c�̹� %target%���� �ʴ� �߽��ϴ�."),
	ALREADY_LEADER("&b&l[&fä�ù�&b&l]&f &c%target%���� �̹� ������ �Դϴ�."),
	
	SET_LEADER_ADMIN("&b&l[&fä�ù�&b&l]&f �����ڿ� ���� %target%���� ���� %chatroom% ä�ù��� ������ �Դϴ�."),
	
	NOW_LEADER_TARGET("&b&l[&fä�ù�&b&l]&f &c%target%���� ���� ������ �Դϴ�."),

	NOW_LEADER_TO_TARGET("&b&l[&fä�ù�&b&l]&f ����� ���� ������ �Դϴ�."),
	
	CANNOT_INVITE_NO_PERM("&b&l[&fä�ù�&b&l]&f &cä�ù� �ʴ�� �����ڸ� �����մϴ�."),
	CANNOT_KICK_ME_OR_LEADER("&b&l[&fä�ù�&b&l]&f &c�ڽ� �Ǵ� �����ڴ� �߹��� �� �� �����ϴ�.!"),
	NOT_SAME_CHATROOM_MEMBER("&b&l[&fä�ù�&b&l]&f &c%target%���� ���� ä�ù� ȸ���� �ƴմϴ�!"),
	NO_CHATROOM_NAME_SPACE("&b&l[&fä�ù�&b&l]&f &cä�ù� �̸��� ���� ���� �ؾ� �մϴ�."),
	
	CONTAINS_NOALLOWED_CHAR("&b&l[&fä�ù�&b&l]&f &c������ ���� ���ڰ� ���ԵǾ� �ֽ��ϴ�. (���� ����: ���ڵ�,\".\",\"#\",\"$\" )"),

	
	KICK_HELP("&b&l[&fä�ù�&b&l]&f /ä�ù� �߹� <�г���> &7: <�г���>���� ���� ä�ù濡�� �߹��մϴ�."),

	KICKED_PLAYER("&b&l[&fä�ù�&b&l]&f &c%target%���� ä�ù濡�� �߹��Ͽ����ϴ�.."),
	KICKED_FROM_CHATROOM("&b&l[&fä�ù�&b&l]&f &c����� %chatroom% ä�ù濡�� �߹� ���Ͽ����ϴ�.."),	
	
	EMPTY_LIST("����"),

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
