# MineChatRoom
## Overview

`MineChatRoom` is a Minecraft plugin that enables group chat functionality by allowing players to join and manage chatrooms. It supports both command-based interactions and a toggle mode for group chat without using commands. (Targetted Korean users)

## Key Features

- **Group Chat Management**: Create and manage chatrooms with ease.
- **Flexible Interaction Modes**: Use commands or toggle mode for group chatting.
- **Detailed Command Set**: Manage chatrooms and their members with specific commands.

## Commands

### Chatroom Management Commands

- **/채팅방관리 생성 (chatroom name) (playername)**: Create a new chatroom named `(chatroom name)` with `(playername)` as the leader.
- **/채팅방관리 계정이전 (기존playername) (새playername)**: Transfer chatroom account information from `(existing playername)` to `(new playername)`.
- **/채팅방관리 해체 <chatroom name>**: Delete the chatroom named `<chatroom name>`.
- **/채팅방관리 추가 <chatroom name> (playername)**: Add `(playername)` as a member to the chatroom named `<chatroom name>`.
- **/채팅방관리 추방 <chatroom name> (playername)**: Expel `(playername)` from the chatroom named `<chatroom name>`.
- **/채팅방관리 스파이**: Spy on conversations happening in the chatroom.
- **/채팅방관리 데이터리로드**: Reload chatroom information from the file.
- **/채팅방관리 설정리로드**: Reload the configuration file.
- **/채팅방관리 데이터저장**: Save the most recently updated chatroom information to the file.
- **/채팅방관리 지도자 <chatroom name> (playername)**: Change the leader of the chatroom named `<chatroom name>` to `(playername)`.

### General Chatroom Commands

- **/채팅방 생성 <chatroom name>**: Create a new chatroom with the name `<chatroom name>`.
- **/채팅방 정보 <chatroom name>**: Show information about the chatroom named `<chatroom name>`.
- **/채팅방 목록 [page number]**: Show a list of chatrooms with pagination.
- **/채팅방 수락**: Accept an invitation to a chatroom.
- **/채팅방 거절**: Decline an invitation to a chatroom.
- **/채팅방 추방 <playername>**: Expel `<playername>` from the current chatroom.
- **/채팅방 초대 <playername>**: Invite `<playername>` to the current chatroom.
- **/채팅방 지도자 <playername>**: Change the leader of the current chatroom to `<playername>`.
- **/채팅방 나가기**: Leave the current chatroom.
- **/채팅방 조회 <playername>**: Lookup the chatroom(s) associated with `<playername>`.
- **/chat**: Switch between chatroom chat mode and general chat mode.
- **/g <채팅내용>**: Send a message to the chatroom.
