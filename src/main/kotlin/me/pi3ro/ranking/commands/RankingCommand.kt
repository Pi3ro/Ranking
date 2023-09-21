package me.pi3ro.ranking.commands

import me.pi3ro.ranking.RankingAPI
import me.pi3ro.ranking.utils.CC
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Created by: Pi3ro
 * Project: Ranking
 * Date: 2023-09-20 @ 21:01
 **/
class RankingCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender?, cmd: Command?, label: String?, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (args != null && args.isNotEmpty()) {
                val subCommand = args[0].toLowerCase()
                val targetPlayerName = args[1]
                val targetPlayer = Bukkit.getPlayer(targetPlayerName)

                if (targetPlayer != null) {
                    val targetPlayerUUID = targetPlayer.uniqueId

                    when (subCommand) {
                        "give" -> {
                            if (args.size >= 3) {
                                val amount = args[2].toIntOrNull()
                                if (amount != null) {
                                    RankingAPI.addPoints(targetPlayerUUID, amount)
                                    sender.sendMessage(CC.translate("&aAdded $amount points to $targetPlayerName's profile."))
                                    return true
                                }
                            }
                        }
                        "remove" -> {
                            if (args.size >= 3) {
                                val amount = args[2].toIntOrNull()
                                if (amount != null) {
                                    RankingAPI.removePoints(targetPlayerUUID, amount)
                                    sender.sendMessage(CC.translate("&cRemoved $amount points from $targetPlayerName's profile."))
                                    return true
                                }
                            }
                        }
                        "set" -> {
                            if (args.size >= 3) {
                                val amount = args[2].toIntOrNull()
                                if (amount != null) {
                                    RankingAPI.setPoints(targetPlayerUUID, amount)
                                    sender.sendMessage(CC.translate("&aSet $targetPlayerName's points to $amount."))
                                    return true
                                }
                            }
                        }
                        "reset" -> {
                            RankingAPI.resetPoints(targetPlayerUUID)
                            sender.sendMessage(CC.translate("&cReset $targetPlayerName's points to 0."))
                            return true
                        }
                        else -> {
                            sender.sendMessage(CC.translate("&cInvalid sub-command. Usage: /ranking [give|remove|set|reset] [profile] [points]"))
                            return true
                        }
                    }
                } else {
                    sender.sendMessage(CC.translate("&cPlayer not found."))
                    return true
                }
            } else {
                sender.sendMessage(CC.translate("&7&m----------------------------------------------"))
                sender.sendMessage(CC.translate("&b&lRanking Commands:"))
                sender.sendMessage(" ")
                sender.sendMessage(CC.translate("&7► &b/ranking give (profile) (points) &7- To give points."))
                sender.sendMessage(CC.translate("&7► &b/ranking remove (profile) (points) &7- To remove points."))
                sender.sendMessage(CC.translate("&7► &b/ranking set (profile) (points) &7- To set points."))
                sender.sendMessage(CC.translate("&7► &b/ranking reset (profile) &7- To reset points."))
                sender.sendMessage(CC.translate("&7&m----------------------------------------------"))
                return true
            }
        }
        return false
    }
}