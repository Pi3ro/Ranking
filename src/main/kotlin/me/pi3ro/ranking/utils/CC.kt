package me.pi3ro.ranking.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor

/**
 * Created by: Pi3ro
 * Project: Ranking
 * Date: 2023-09-20 @ 19:56
 **/
object CC {
    fun translate(msg: String?): String{ return ChatColor.translateAlternateColorCodes('&', msg) }
    fun console(msg: String?) { Bukkit.getServer().consoleSender.sendMessage(translate(msg)) }
}