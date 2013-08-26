/*
 * This file is part of the Spout plugin TLDScape. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDScape.command;

import com.github.thelonedevil.TLDScape.Mob.Guide;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDScape.TLDScapePlugin;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.discrete.Point;
import org.spout.vanilla.component.entity.living.Living;

/**
 * Provides an example of a class to hold commands.
 */
public class TLDScapeCommands {
    
    private final TLDScapePlugin plugin;
    
    public TLDScapeCommands(TLDScapePlugin instance) {
        this.plugin = instance;
    }

    /**
     * Provides an example command that can be issued to the Spout server.
     */
    @CommandDescription(aliases = {"command", "cmd"}, desc = "This is an example of what a command might look like. Try it out with /cmd !")
    @Permissible("TLDScape.some.permission")
    public void exampleCommand(CommandSource source, CommandArguments args) throws CommandException {

        // Calling this command will send whoever issued it the message below.
        source.sendMessage("The TLDScape plugin command has been successfully issued.");
        Player player = (Player) source;
        Point point = player.getPhysics().getPosition();
        Entity entity = player.getWorld().createEntity(player.getPhysics().getPosition(), Guide.class);
        player.getWorld().spawnEntity(entity);
    }
}
