package com.spaceapps.backend.model.dto.profile

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class ProfileResponse(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("first_name")
    val firstName: String? = null,
    @JsonProperty("last_name")
    val lastName: String? = null,
    @JsonProperty("image_url")
    val imageUrl: String? = null,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("birth_date")
    val birthDate: LocalDate? = null,
    @JsonProperty("phone")
    val phone: String? = null,
    @JsonProperty("address")
    val address: String? = null,
    @JsonProperty("city")
    val city: String? = null,
    @JsonProperty("region")
    val region: String? = null,
    @JsonProperty("country")
    val country: String? = null,
    @JsonProperty("zip_code")
    val zipCode: String? = null
)