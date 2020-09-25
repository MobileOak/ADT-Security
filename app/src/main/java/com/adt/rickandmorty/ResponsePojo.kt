package com.adt.rickandmorty

import java.util.*

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
class Info {
    var count = 0
    var pages = 0
    var next: String? = null
    var prev: Any? = null
}

class Origin {
    var name: String? = null
    var url: String? = null
}

class Location {
    var name: String? = null
    var url: String? = null
}

class Result {
    var id = 0
    var name: String? = null
    var status: String? = null
    var species: String? = null
    var type: String? = null
    var gender: String? = null
    var origin: Origin? = null
    var location: Location? = null
    var image: String? = null
    var episode: List<String>? = null
    var url: String? = null
    var created: Date? = null
}

class Root {
    var info: Info? = null
    var results: List<Result>? = null
}

