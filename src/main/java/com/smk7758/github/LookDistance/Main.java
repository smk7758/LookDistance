package com.smk7758.github.LookDistance;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		saveConfig();
	}

	public void onDisable() {
	}

	public void setLoc(Player player) {
		World world = player.getWorld();
		double x = player.getLocation().getX();
		double y = player.getLocation().getY();
		double z = player.getLocation().getZ();
		double yaw = player.getLocation().getYaw();
		double pitch = player.getLocation().getPitch();
		getConfig().set("Point.World", world.getName());
		getConfig().set("Point.X", x);
		getConfig().set("Point.Y", y);
		getConfig().set("Point.Z", z);
		getConfig().set("Point.Yaw", yaw);
		getConfig().set("Point.Pitch", pitch);
		saveConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("LookDistance")) {
			if (sender instanceof Player) {
				if (getConfig().contains("Point")) {
					Player player = (Player)sender;
					Location loc = player.getLocation();
					double distance_x;
					double distance_y;
					double distance_z;
					if (loc.getX() > getConfig().getDouble("Point.X")) {
						distance_x = loc.getX() - getConfig().getDouble("Point.X");
					} else {
						distance_x = getConfig().getDouble("Point.X") - loc.getX();
					}
					if (loc.getY() > getConfig().getDouble("Point.Y")) {
						distance_y = loc.getY() - getConfig().getDouble("Point.Y");
					} else {
						distance_y = getConfig().getDouble("Point.Y") - loc.getY();
					}
					if (loc.getZ() > getConfig().getDouble("Point.Z")) {
						distance_z = loc.getZ() - getConfig().getDouble("Point.Y");
					} else {
						distance_z = getConfig().getDouble("Point.Z") - loc.getZ();
					}
					double distance = Math.sqrt(distance_x*distance_x + distance_y*distance_y + distance_z*distance_z);
//					double distance = Math.sqrt(loc.getX()*getConfig().getDouble("Point.X")+loc.getY()*getConfig().getDouble("Point.Y")+loc.getZ()*getConfig().getDouble("Point.Z"));
					player.sendMessage("TEST_Distance:" + distance);
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("SetDistance")) {
			if (sender instanceof Player) {
				Player player = (Player)sender;
				setLoc(player);
				return true;
			}
		}
	return false;
	}
}
