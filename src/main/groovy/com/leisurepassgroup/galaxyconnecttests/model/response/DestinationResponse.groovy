package com.leisurepassgroup.galaxyconnecttests.model.response

import com.fasterxml.jackson.annotation.JsonCreator

class DestinationResponse {
    String id
    String name

    @JsonCreator
    DestinationResponse(id, name) {
        this.id = id
        this.name = name
    }
}
