package com.leisurepassgroup.galaxyconnecttests.model.request

class TicketActivationRequest {
    String ticketNumber
    String productCode
    String supplierName
    Integer resultCode
    String correlationId
    TicketActivationRequest errors
}
