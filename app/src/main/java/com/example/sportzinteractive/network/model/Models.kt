package com.example.sportzinteractive.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Team(
    @SerializedName("Name_Full") var nameFull: String? = null,
    @SerializedName("Name_Short") var nameShort: String? = null,
    @SerializedName("Players") var player: Map<String, PlayerDetail>? = null
)

data class PlayerDetail(
    @SerializedName("Position") var Position: String? = null,
    @SerializedName("Name_Full") var NameFull: String? = null,
    @SerializedName("Iskeeper") var isKeeper: Boolean = false,
    @SerializedName("Iscaptain") var isCaptain: Boolean = false,
    @SerializedName("Batting") var Batting: Batting? = Batting(),
    @SerializedName("Bowling") var Bowling: Bowling? = Bowling()
)

data class Bowling(
    @SerializedName("Style") var Style: String? = null,
    @SerializedName("Average") var Average: String? = null,
    @SerializedName("Economyrate") var Economyrate: String? = null,
    @SerializedName("Wickets") var Wickets: String? = null
)

data class Batting(
    @SerializedName("Style") var Style: String? = null,
    @SerializedName("Average") var Average: String? = null,
    @SerializedName("Strikerate") var Strikerate: String? = null,
    @SerializedName("Runs") var Runs: String? = null
)

data class Innings(
    @SerializedName("Number") var Number: String? = null,
    @SerializedName("Battingteam") var Battingteam: String? = null,
    @SerializedName("Total") var Total: String? = null,
    @SerializedName("Wickets") var Wickets: String? = null,
    @SerializedName("Overs") var Overs: String? = null,
    @SerializedName("Runrate") var Runrate: String? = null,
    @SerializedName("Byes") var Byes: String? = null,
    @SerializedName("Legbyes") var Legbyes: String? = null,
    @SerializedName("Wides") var Wides: String? = null,
    @SerializedName("Noballs") var Noballs: String? = null,
    @SerializedName("Penalty") var Penalty: String? = null,
    @SerializedName("AllottedOvers") var AllottedOvers: String? = null,
    @SerializedName("Batsmen") var Batsmen: ArrayList<Batsmen>? = null,
    @SerializedName("Partnership_Current") var PartnershipCurrent: PartnershipCurrent? = PartnershipCurrent(),
    @SerializedName("Bowlers") var Bowlers: ArrayList<Bowlers>? = null,
    @SerializedName("FallofWickets") var FallofWickets: ArrayList<FallofWickets>? = null,
    @SerializedName("PowerPlay") var PowerPlay: Map<String,String>? = null
)

data class PowerPlay(
    @SerializedName("PowerPlay") var powerPlay: Map<String, String>? = null,
)

data class FallofWickets(
    @SerializedName("Batsman") var Batsman: String? = null,
    @SerializedName("Score") var Score: String? = null,
    @SerializedName("Overs") var Overs: String? = null
)

data class Bowlers(
    @SerializedName("Bowler") var Bowler: String? = null,
    @SerializedName("Overs") var Overs: String? = null,
    @SerializedName("Maidens") var Maidens: String? = null,
    @SerializedName("Runs") var Runs: String? = null,
    @SerializedName("Wickets") var Wickets: String? = null,
    @SerializedName("Economyrate") var Economyrate: String? = null,
    @SerializedName("Noballs") var Noballs: String? = null,
    @SerializedName("Wides") var Wides: String? = null,
    @SerializedName("Dots") var Dots: String? = null
)

data class PartnershipCurrent(
    @SerializedName("Runs") var Runs: String? = null,
    @SerializedName("Balls") var Balls: String? = null,
    @SerializedName("Batsmen") var Batsmen: ArrayList<Batsmen>? = null
)

data class Batsmen(
    @SerializedName("Batsman") var Batsman: String? = null,
    @SerializedName("Runs") var Runs: String? = null,
    @SerializedName("Balls") var Balls: String? = null,
    @SerializedName("Fours") var Fours: String? = null,
    @SerializedName("Sixes") var Sixes: String? = null,
    @SerializedName("Dots") var Dots: String? = null,
    @SerializedName("Strikerate") var Strikerate: String? = null,
    @SerializedName("Dismissal") var Dismissal: String? = null,
    @SerializedName("Howout") var Howout: String? = null,
    @SerializedName("Bowler") var Bowler: String? = null,
    @SerializedName("Fielder") var Fielder: String? = null
)

data class Matchdetail(
    @SerializedName("Team_Home") var teamHome: String? = null,
    @SerializedName("Team_Away") var teamAway: String? = null,
    @SerializedName("Match") var match: Match? = Match(),
    @SerializedName("Series") var series: Series? = Series(),
    @SerializedName("Venue") var venue: Venue? = Venue(),
    @SerializedName("Officials") var officials: Officials? = Officials(),
    @SerializedName("Weather") var weather: String? = null,
    @SerializedName("Tosswonby") var tossWonBy: String? = null,
    @SerializedName("Status") var status: String? = null,
    @SerializedName("Status_Id") var statusId: String? = null,
    @SerializedName("Player_Match") var playerMatch: String? = null,
    @SerializedName("Result") var result: String? = null,
    @SerializedName("Winningteam") var winningteam: String? = null,
    @SerializedName("Winmargin") var winmargin: String? = null,
    @SerializedName("Equation") var equation: String? = null
)

data class Officials(
    @SerializedName("Umpires") var Umpires: String? = null,
    @SerializedName("Referee") var Referee: String? = null
)

data class Venue(
    @SerializedName("Id") var Id: String? = null,
    @SerializedName("Name") var Name: String? = null
)

data class Series(
    @SerializedName("Id") var id: String? = null,
    @SerializedName("Name") var name: String? = null,
    @SerializedName("Status") var status: String? = null,
    @SerializedName("Tour") var tour: String? = null,
    @SerializedName("Tour_Name") var tourName: String? = null
)

data class Match(
    @SerializedName("Livecoverage") var liveCoverage: String? = null,
    @SerializedName("Id") var id: String? = null,
    @SerializedName("Code") var code: String? = null,
    @SerializedName("League") var league: String? = null,
    @SerializedName("Number") var number: String? = null,
    @SerializedName("Type") var type: String? = null,
    @SerializedName("Date") var date: String? = null,
    @SerializedName("Time") var time: String? = null,
    @SerializedName("Offset") var offset: String? = null,
    @SerializedName("Daynight") var dayNight: String? = null
)

data class ResponseData(
    @SerializedName("Matchdetail") var matchDetail: Matchdetail? = Matchdetail(),
    @SerializedName("Nuggets") var nuggets: ArrayList<String>? = null,
    @SerializedName("Innings") var innings: ArrayList<Innings>? = null,
    @SerializedName("Teams") var teams: Map<String,Team>? = null,
    @SerializedName("Notes") val notes: Map<String, ArrayList<String>>? = null
)