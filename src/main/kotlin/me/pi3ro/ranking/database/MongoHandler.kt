package me.pi3ro.ranking.database

import com.mongodb.MongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import me.pi3ro.ranking.Ranking
import me.pi3ro.ranking.utils.CC
import org.bukkit.Bukkit
import java.util.logging.Level
import java.util.logging.Logger

/**
 * Created by: Pi3ro
 * Project: Ranking
 * Date: 2023-09-20 @ 19:55
 **/
class MongoHandler(private val plugin: Ranking) {
    private lateinit var mongoClient: MongoClient
    init {
        Logger.getLogger("org.mongodb.driver").level = Level.OFF
        try {
            if (plugin.config.getBoolean("MONGODB.AUTHENTICATION.ENABLED")) {
                val credential = MongoCredential.createCredential(
                    plugin.config.getString("MONGODB.AUTHENTICATION.USERNAME"),
                    plugin.config.getString("MONGODB.AUTHENTICATION.DATABASE"),
                    plugin.config.getString("MONGODB.AUTHENTICATION.PASSWORD").toCharArray()
                )

                mongoClient = MongoClient(
                    ServerAddress(
                        plugin.config.getString("MONGODB.ADDRESS"),
                        plugin.config.getInt("MONGODB.PORT")
                    ),
                    listOf(credential)
                )
            } else {
                mongoClient = MongoClient(
                    plugin.config.getString("MONGODB.ADDRESS"),
                    plugin.config.getInt("MONGODB.PORT")
                )
            }
            plugin.mongoDatabase = mongoClient.getDatabase(plugin.config.getString("MONGODB.DATABASE"))
        } catch (e: Exception) {
            e.printStackTrace()
            CC.console("&c&lError! &cMongoDB.")
            Bukkit.getPluginManager().disablePlugin(plugin)
        }
    }
}