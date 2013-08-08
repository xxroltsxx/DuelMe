package com.teozcommunity.teozfrank.duelme.commands;

import com.teozcommunity.teozfrank.duelme.main.DuelMe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 06/08/13
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
public class DuelCommand implements CommandExecutor {

    private DuelMe plugin;

    public DuelCommand(DuelMe plugin){
      this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;

            int length = args.length;

            if(length<1){
              p.sendMessage(ChatColor.GREEN + "0o--------------- " + plugin.pluginPrefix + ChatColor.GOLD + "PVP for fun" + ChatColor.GREEN + " ---------------o0\n\n" +

                      ChatColor.GREEN + "/duel <player> " + ChatColor.GOLD + "- Duel a specified player \n" +
                      ChatColor.GREEN + "/duel accept " + ChatColor.GOLD + "- Accept a duel request \n" +
                      ChatColor.GREEN + "/duel deny " + ChatColor.GOLD + "- Deny a duel request \n" +
                      ChatColor.GREEN + "/duel spectate " + ChatColor.GOLD + "- Spectate a duel in progress \n\n" +

                      ChatColor.GREEN + "0o--------------" + ChatColor.GOLD + " V" + plugin.version + " BETA by TeOzFrAnK " + ChatColor.GREEN + "-------------o0\n" +
                      ChatColor.GREEN + "0o-----" + ChatColor.GOLD + " http://dev.bukkit.org/bukkit-plugins/duelme/ " + ChatColor.GREEN + "------o0"

              );
                return true;
            }

            else if(length==1){
                if(args[0].equals("accept")){
                  plugin.util.acceptDuel(p.getPlayer());
                }
                else if(args[0].equals("deny")){
                    plugin.util.denyDuel(p.getPlayer());
                }
                else if(args[0].equals("spectate")){
                    plugin.util.spectateDuel(p.getPlayer());
                }
                else if(args[0].equals("leave")){
                    plugin.util.leaveDuel(p.getPlayer());
                }
                else {

                    Player targetPlayer = Bukkit.getPlayer(args[0]);
                    if(targetPlayer!=null){
                        plugin.util.sendRequest(p.getPlayer(),targetPlayer.getPlayer());
                    }
                    else{
                        p.sendMessage(plugin.pluginPrefix+ChatColor.RED+"Player "+ChatColor.GOLD+args[0]+ChatColor.RED+" is not online! Did you type it correctly?");
                    }

                }

                return true;
            }

            else {
                p.sendMessage(plugin.pluginPrefix+ChatColor.RED+"Unknown command!");
                return true;
            }
        }

        else {
            sender.sendMessage("Players can only execute this command!");
            return true;
        }
    }
}
