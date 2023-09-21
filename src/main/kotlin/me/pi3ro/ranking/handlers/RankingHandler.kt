package me.pi3ro.ranking.handlers

import me.pi3ro.ranking.profile.Profile

/**
 * Created by: Pi3ro
 * Project: Ranking
 * Date: 2023-09-20 @ 20:32
 **/
class RankingHandler {
    companion object {
        fun updateRank(profile: Profile) {
            when {
                profile.points >= 250 -> profile.ranking = "&6&lRadiant"
                profile.points >= 230 -> profile.ranking = "&cImmortal III"
                profile.points >= 220 -> profile.ranking = "&cImmortal II"
                profile.points >= 210 -> profile.ranking = "&cImmortal I"
                profile.points >= 200 -> profile.ranking = "&2Ascendant III"
                profile.points >= 190 -> profile.ranking = "&2Ascendant II"
                profile.points >= 180 -> profile.ranking = "&2Ascendant I"
                profile.points >= 170 -> profile.ranking = "&dDiamond III"
                profile.points >= 160 -> profile.ranking = "&dDiamond II"
                profile.points >= 150 -> profile.ranking = "&dDiamond I"
                profile.points >= 140 -> profile.ranking = "&3Platinum III"
                profile.points >= 130 -> profile.ranking = "&3Platinum II"
                profile.points >= 120 -> profile.ranking = "&3Platinum I"
                profile.points >= 110 -> profile.ranking = "&6Gold III"
                profile.points >= 100 -> profile.ranking = "&6Gold II"
                profile.points >= 90 -> profile.ranking = "&6Gold I"
                profile.points >= 80 -> profile.ranking = "&7Silver III"
                profile.points >= 70 -> profile.ranking = "&7Silver II"
                profile.points >= 60 -> profile.ranking = "&7Silver I"
                profile.points >= 50 -> profile.ranking = "&eBronze III"
                profile.points >= 40 -> profile.ranking = "&eBronze II"
                profile.points >= 30 -> profile.ranking = "&eBronze I"
                profile.points >= 20 -> profile.ranking = "&8Iron III"
                profile.points >= 10 -> profile.ranking = "&8Iron II"
                else -> profile.ranking = "&8Iron I"
            }
            profile.save()
        }
    }
}
