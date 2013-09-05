package com.github.thelonedevil.TLDNPC.gui;

import org.spout.api.entity.Player;

import com.github.thelonedevil.TLDNPC.mobs.NPC;

public class NPCWindow {
	private String message = "I AM AN NPC!";
	private NPC npc;
	private Player player;

	public NPCWindow(NPC npc, String Message, Player player) {
		this.message = Message;
		this.npc = npc;
		this.player = player;
		create();
	}

	public NPCWindow(NPC npc, Player player) {
		this.npc = npc;
		this.player = player;
		create();
	}

	private void create() {
		//TODO ADD GUI STUFF
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
