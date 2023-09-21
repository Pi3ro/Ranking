# Ranking
Ranking is a plugin based on Valorant ranks with points.

## Usage

```kotlin
RankingAPI#addPoints(playerUUID: UUID, amount: Int)
RankingAPI#setPoints(playerUUID: UUID, amount: Int)
RankingAPI#removePoints(playerUUID: UUID, amount: Int)
RankingAPI#resetPoints(playerUUID: UUID)
RankingAPI#getPoints(playerUUID: UUID)
RankingAPI#getRanking(playerUUID: UUID)
```
