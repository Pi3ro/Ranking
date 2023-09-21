package me.pi3ro.ranking

import com.mongodb.client.MongoDatabase
import me.pi3ro.ranking.database.MongoHandler
import me.pi3ro.ranking.handlers.MiscHandler
import me.pi3ro.ranking.profile.Profile
import me.pi3ro.ranking.utils.CC
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * Created by: Pi3ro
 * Project: Ranking
 * Date: 2023-09-20 @ 19:29
 **/
class Ranking : JavaPlugin() {
    lateinit var mongoHandler: MongoHandler
    lateinit var mongoDatabase: MongoDatabase

    override fun onEnable() {
        instance = this
        MiscHandler(this)
        loadConfig()
        val time = System.currentTimeMillis()
        logger("&m------------------------------------------------")
        logger("          &b&lRanking")
        logger(" ")
        logger(" &7| &bVersion: &f" + description.version)
        logger(" &7| &bAuthor: &f" + description.authors)
        logger(" &7| &bInitialized in &f" + (System.currentTimeMillis() - time) + " &bms")
        logger(" ")
        logger("&m------------------------------------------------")
    }

    override fun onDisable() {
        Profile.getProfiles().values.forEach { profile -> profile.save() }
    }

    private fun loadConfig() {
        val config = File(dataFolder, "config.yml")
        if (!config.exists()){
            getConfig().options().copyDefaults(true)
            saveConfig()
        }
    }

    private fun logger(msg: String?) { Bukkit.getConsoleSender().sendMessage(CC.translate(msg)) }

    companion object {
        private lateinit var instance: Ranking
        @JvmStatic
        fun getInstance(): Ranking {
            return instance
        }
    }
}