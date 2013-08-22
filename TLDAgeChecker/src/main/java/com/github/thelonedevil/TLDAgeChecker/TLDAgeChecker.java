package com.github.thelonedevil.TLDAgeChecker;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;

import com.github.thelonedevil.TLDAgeChecker.commands.PlayerCommands;


public class TLDAgeChecker extends Plugin {
	static String error = "An error has happened...... incoming stack trace....";
	
    @Override
	public void onEnable(){
    	AnnotatedCommandExecutorFactory.create(new PlayerCommands(this));
    	getEngine().getEventManager().registerEvents(new EListener(this), this);
    }
    
    @Override
	public void onDisable(){
    }
}
