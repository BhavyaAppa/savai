package com.example.alokozapshopapplication.ui.fragment.searchfragment.model.searchmodel

import com.fasterxml.jackson.annotation.JsonProperty

data class SearchDataRequest(



    @JsonProperty("websiteId")
    var websiteId: String = "1",
    @JsonProperty("storeId")
    var storeId: String = "1",
    @JsonProperty("q")
    var q: String ,
    @JsonProperty("cat")
    var cat: Boolean = false,

)
