package com.spaceapps.backend.service

import com.spaceapps.backend.INCORRECT_USER_ID
import com.spaceapps.backend.model.dao.auth.UserEntity
import com.spaceapps.backend.model.dto.profile.ProfileRequest
import com.spaceapps.backend.model.dto.profile.ProfileResponse
import com.spaceapps.backend.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProfileService(
    private val userRepository: UserRepository
) {

    fun getProfileById(id: Int): ResponseEntity<*> {
        val optional = userRepository.findById(id)
        return if (optional.isPresent) {
            ResponseEntity.ok(mapProfileEntityToResponse(optional.get()))
        } else {
            ResponseEntity.badRequest().body(INCORRECT_USER_ID)
        }
    }

    fun updateProfileById(id: Int, request: ProfileRequest): ResponseEntity<*> {
        val entity = userRepository.findById(id).get()
        val newEntity = entity.copy(
            firstName = request.firstName ?: entity.firstName,
            lastName = request.lastName ?: entity.lastName,
            imageUrl = request.imageUrl ?: entity.imageUrl,
            birthDate = request.birthDate ?: entity.birthDate,
            phone = request.phone ?: entity.phone,
            address = request.address ?: entity.address,
            city = request.city ?: entity.city,
            region = request.region ?: entity.region,
            country = request.country ?: entity.country,
            zipCode = request.zipCode ?: entity.zipCode,
        )
        return ResponseEntity.ok(mapProfileEntityToResponse(userRepository.save(newEntity)))
    }

    private fun mapProfileEntityToResponse(entity: UserEntity): ProfileResponse {
        return ProfileResponse(
            id = entity.id,
            firstName = entity.firstName,
            lastName = entity.lastName,
            imageUrl = entity.imageUrl,
            email = entity.email,
            birthDate = entity.birthDate,
            phone = entity.phone,
            address = entity.address,
            city = entity.city,
            region = entity.region,
            country = entity.country,
            zipCode = entity.zipCode
        )
    }
}