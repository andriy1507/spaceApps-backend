package com.spaceapps.backend.service

import com.spaceapps.backend.DEVICE_ALREADY_REGISTERED
import com.spaceapps.backend.DEVICE_NOT_FOUND
import com.spaceapps.backend.model.dao.auth.DeviceEntity
import com.spaceapps.backend.model.dao.auth.UserEntity
import com.spaceapps.backend.model.dto.auth.DeviceRequest
import com.spaceapps.backend.repository.DevicesRepository
import com.spaceapps.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DevicesService @Autowired constructor(
    private val userRepository: UserRepository,
    private val devicesRepository: DevicesRepository
) {

    fun saveDeviceForUser(user: UserEntity, request: DeviceRequest) {
        if (devicesRepository.existsByToken(request.token)) throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            DEVICE_ALREADY_REGISTERED
        )
        val device = DeviceEntity(
            token = request.token,
            platform = when (request.platform) {
                DeviceRequest.Platform.Android -> DeviceEntity.Platform.Android
                DeviceRequest.Platform.Ios -> DeviceEntity.Platform.Ios
            }
        )
        devicesRepository.save(device)
        user.devices += devicesRepository.save(device)
        userRepository.save(user)
    }

    fun deleteDeviceForUser(user: UserEntity, token: String) {
        val device = devicesRepository.findByToken(token)
        device ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, DEVICE_NOT_FOUND)
        user.devices.remove(device)
        userRepository.save(user)
        devicesRepository.delete(device)
    }
}