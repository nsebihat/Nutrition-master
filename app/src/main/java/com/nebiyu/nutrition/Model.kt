package com.nebiyu.nutrition

class HomeFeed(val next: String, val results: List<Nut>)

class Nut(val id: Int, val name: String, val energy: String, val protein: String, val carbohydrates:String,
val carbohydrates_sugar:String, val fat:String, val fat_saturated:String, val fibres:String, val sodium:String)

