package me.pi3ro.ranking.profile.listener

import me.pi3ro.ranking.profile.Profile
import me.pi3ro.ranking.utils.CC.translate
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

/**
 * Created by: Pi3ro
 * Project: Ranking
 * Date: 2023-09-20 @ 20:06
 **/
class ProfileListener : Listener {

    @EventHandler
    private fun onAsyncPlayerJoin(event: AsyncPlayerPreLoginEvent) {
        val profile = Profile(event.uniqueId)
        try {
            profile.load()
            Profile.addProfile(event.uniqueId, profile)
        } catch (e: Exception) {
            e.printStackTrace()
            event.loginResult = AsyncPlayerPreLoginEvent.Result.KICK_OTHER
            event.kickMessage = translate("&cFailed to load your profile.")
        }
    }

    @EventHandler
    private fun onPlayerJoin(event: PlayerJoinEvent) {
        val profile: Profile = Profile.getByUuid(event.player.uniqueId)!!
        profile.name = event.player.name
    }

    @EventHandler
    private fun onPlayerQuit(event: PlayerQuitEvent) {
        val profile: Profile = Profile.getByUuid(event.player.uniqueId)!!
        profile.save()
        Profile.removeProfile(event.player.uniqueId)
    }

    @EventHandler
    private fun handleChatFormat(event: AsyncPlayerChatEvent) {
        val player = event.player
        var message = event.message
        val profile: Profile = Profile.getByUuid(player.uniqueId)!!
        message = message.replace("%", "%%")
        event.format  = translate("&7[${profile.ranking}&7] ${player.displayName}&7: &f$message")
    }
}