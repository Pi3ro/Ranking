package me.pi3ro.ranking

import me.pi3ro.ranking.handlers.RankingHandler
import me.pi3ro.ranking.profile.Profile
import java.util.*

/**
 * Created by: Pi3ro
 * Project: Ranking
 * Date: 2023-09-20 @ 20:55
 **/
class RankingAPI {
    companion object {
        /**
         * Add points to a player's profile.
         *
         * @param playerUUID The UUID of the player.
         * @param amount The amount of points to add.
         */
        fun addPoints(playerUUID: UUID, amount: Int) {
            val profile = Profile.getByUuid(playerUUID)
            if (profile != null) {
                profile.points += amount
                RankingHandler.updateRank(profile)
                profile.save()
            }
        }

        /**
         * Set the points of a player's profile.
         *
         * @param playerUUID The UUID of the player.
         * @param amount The new amount of points to set.
         */
        fun setPoints(playerUUID: UUID, amount: Int) {
            val profile = Profile.getByUuid(playerUUID)
            if (profile != null) {
                profile.points = maxOf(amount, 0)
                RankingHandler.updateRank(profile)
                profile.save()
            }
        }

        /**
         * Remove points from a player's profile.
         *
         * @param playerUUID The UUID of the player.
         * @param amount The amount of points to remove.
         */
        fun removePoints(playerUUID: UUID, amount: Int) {
            val profile = Profile.getByUuid(playerUUID)
            if (profile != null) {
                profile.points = maxOf(profile.points - amount, 0)
                RankingHandler.updateRank(profile)
                profile.save()
            }
        }

        /**
         * Reset a player's points to 0.
         *
         * @param playerUUID The UUID of the player.
         */
        fun resetPoints(playerUUID: UUID) {
            val profile = Profile.getByUuid(playerUUID)
            if (profile != null) {
                profile.points = 0
                RankingHandler.updateRank(profile)
                profile.save()
            }
        }

        /**
         * Get the points of a player's profile.
         *
         * @param playerUUID The UUID of the player.
         * @return The player's points.
         */
        fun getPoints(playerUUID: UUID): Int {
            val profile = Profile.getByUuid(playerUUID)
            return profile?.points ?: 0
        }

        /**
         * Get the name of a player's ranking based on their profile.
         *
         * @param playerUUID The UUID of the player.
         * @return The name of the player's ranking.
         */
        fun getRanking(playerUUID: UUID): String {
            val profile = Profile.getByUuid(playerUUID)
            return profile?.ranking ?: "&8Iron I"
        }
    }
}
