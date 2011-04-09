package fabulous;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerChatEvent;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijikokun.bukkit.Permissions.Permissions;
import com.nijiko.permissions.PermissionHandler;

public class Fabulous extends JavaPlugin {
	public static PermissionHandler Permissions;
	private  Plugin perm; 
	
	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
		setupPermissions();
		PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}
	private void setupPermissions() {
	      perm = this.getServer().getPluginManager().getPlugin("Permissions");

	      if (this.Permissions == null) {
	          if (perm != null) {
	              this.Permissions = ((Permissions) perm).getHandler();
	          } else {
	              System.out.println("[Fabulous] Permission system not detected, defaulting to OP");
	          }
	      }
	}
	protected void faba(CommandSender sender, String [] args) {
		Player player = (Player) sender;
		if(perm != null && !this.Permissions.has(player, "fabulous.faba")) {
			player.sendMessage("You don't have access to this command.");
			return;
		}
		if (perm == null && !player.isOp()) {
			player.sendMessage("You don't have access to this command.");
			return;
		}
		String msg = "";
		final Server server = getServer();
		PluginManager pm = server.getPluginManager();
		
		int i = 1;
		
		for (String string : args) {
			for(int j=0; j<string.length(); j++) {
				if(i>16) i=1;
				msg += getColora(i) +string.charAt(j);
				i++;
			}
			msg += " ";
		}
		
		PlayerChatEvent chatEvent =  new PlayerChatEvent(player, msg);
		pm.callEvent(chatEvent);
		if( !chatEvent.isCancelled() ) {
		    for( Player current : chatEvent.getRecipients()) {
		        current.sendMessage( "<" + player.getDisplayName() + "> " + chatEvent.getMessage());
		    }
		}
	}
	protected void fab(CommandSender sender, String [] args) {
		Player player = (Player) sender;
		if(perm != null && !this.Permissions.has(player, "fabulous.fab")) {
			player.sendMessage("You don't have access to this command.");
			return;
		}
		if (perm == null && !player.isOp()) {
			player.sendMessage("You don't have access to this command.");
			return;
		}
		String msg = "";
		final Server server = getServer();
		PluginManager pm = server.getPluginManager();
		
		int i = 1;
		
		for (String string : args) {
			for(int j=0; j<string.length(); j++) {
				if(i>7) i=1;
				msg += getColor(i) +string.charAt(j);
				i++;
			}
			msg += " ";
		}
		
		PlayerChatEvent chatEvent = new PlayerChatEvent(player, msg);
		pm.callEvent(chatEvent);
		if( !chatEvent.isCancelled() ) {
		    for( Player current : chatEvent.getRecipients()) {
		        current.sendMessage( "<" + player.getDisplayName() + "> " + chatEvent.getMessage());
		    }
		}
	}
	private String getColora(int i) {
		String farbe;
		switch (i) {
		case 1: farbe = "0"; break;
		case 2: farbe = "1"; break;
		case 3: farbe = "2"; break;
		case 4: farbe = "3"; break;
		case 5: farbe = "4"; break;
		case 6: farbe = "5"; break;
		case 7: farbe = "6"; break;
		case 8: farbe = "7"; break;
		case 9: farbe = "8"; break;
		case 10: farbe = "9"; break;
		case 11: farbe = "A"; break;
		case 12: farbe = "B"; break;
		case 13: farbe = "C"; break;
		case 14: farbe = "D"; break;
		case 15: farbe = "E"; break;
		case 16: farbe = "F"; break;
		default: farbe = "F";
			break;
		}
		return "\u00A7"+farbe;
	}
	private String getColor(int i) {
		String farbe;
		switch (i) {
		case 1: farbe = "6"; break;  // gold
		case 2: farbe = "A"; break; // green
		case 3: farbe = "B"; break;
		case 4: farbe = "C"; break;
		case 5: farbe = "D"; break;
		case 6: farbe = "E"; break;
		case 7: farbe = "F"; break;
		default: farbe = "F";
			break;
		}
		return "\u00A7"+farbe;
	}
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		String cmd = command.getName().toLowerCase();
		if (cmd.equals("fab")) {
			fab(sender, args);
			return true;
		}
		if (cmd.equals("faba")) {
			faba(sender, args);
			return true;
		}
		return false;
	}
}
