package me.pi3ro.ranking.handlers

import me.pi3ro.ranking.Ranking
import me.pi3ro.ranking.commands.RankingCommand
import me.pi3ro.ranking.database.MongoHandler
import me.pi3ro.ranking.profile.Profile
import me.pi3ro.ranking.profile.listener.ProfileListener
import org.bukkit.Bukkit

/**
 * Created by: Pi3ro
 * Project: Ranking
 * Date: 2023-09-20 @ 20:00
 **/
class MiscHandler(private val plugin: Ranking) {
    init {
        register()
    }

    fun register() {
        loadListeners()
        loadCommands()
        plugin.mongoHandler = MongoHandler(plugin)
        Profile.init()
    }

    private fun loadListeners(){
        Bukkit.getPluginManager().registerEvents(ProfileListener(), plugin)
    }

    private fun loadCommands() {
        val rankingCommand = plugin.getCommand("ranking")
        rankingCommand?.executor = RankingCommand()
        rankingCommand?.permission = "ranking.admin"
    }
}
