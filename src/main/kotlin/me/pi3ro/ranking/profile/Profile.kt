package me.pi3ro.ranking.profile

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.ReplaceOptions
import me.pi3ro.ranking.Ranking
import me.pi3ro.ranking.utils.CC
import org.bson.Document
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by: Pi3ro
 * Project: Ranking
 * Date: 2023-09-20 @ 20:26
 **/
class Profile(private val uuid: UUID) {
    var name: String = ""
    var points: Int = 0
    var ranking: String = "&8Iron I"

    companion object {
        private val profiles: MutableMap<UUID, Profile> = ConcurrentHashMap()
        lateinit var collection: MongoCollection<Document>

        fun init() {
            collection = Ranking.getInstance().mongoDatabase.getCollection("profiles")

            for (player in Bukkit.getOnlinePlayers()) {
                val profile = Profile(player.uniqueId)
                profiles[player.uniqueId] = profile

                try {
                    profile.load()
                } catch (e: Exception) {
                    player.kickPlayer(CC.translate("&c&lThe server is loading..."))
                }
            }
        }

        fun getProfiles(): Map<UUID, Profile> { return profiles }
        fun addProfile(uuid: UUID, profile: Profile) { profiles[uuid] = profile }
        fun removeProfile(uuid: UUID) { profiles.remove(uuid) }
        fun getByUuid(uuid: UUID): Profile? { return profiles[uuid] }
    }

    fun load() {
        try {
            val document = collection.find(Filters.eq("uuid", uuid.toString())).first()
            if (document == null) {
                save()
                return
            }
            name = document.getString("name")
            points = document.getInteger("points") ?: 0
            ranking = document.getString("ranking") ?: "Invalid"
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    fun save() {
        val document = Document()
        document["uuid"] = uuid.toString()
        document["name"] = name
        document["points"] = points
        document["ranking"] = ranking
        collection.replaceOne(Filters.eq("uuid", uuid.toString()), document, ReplaceOptions().upsert(true))
    }
}