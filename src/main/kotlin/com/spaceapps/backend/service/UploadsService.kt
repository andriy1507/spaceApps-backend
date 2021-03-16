package com.spaceapps.backend.service

import com.spaceapps.backend.INCORRECT_FILE_NAME
import com.spaceapps.backend.model.dao.uploads.UploadEntity
import com.spaceapps.backend.model.dto.uploads.UploadResponse
import com.spaceapps.backend.model.dto.uploads.UploadType
import com.spaceapps.backend.repository.UploadsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@Service
class UploadsService @Autowired constructor(
    private val repository: UploadsRepository
) {


    fun saveFile(file: MultipartFile, type: UploadType): ResponseEntity<*> {
        val extension = file.originalFilename?.substringAfterLast('.')
        val entity = UploadEntity(
            name = "${UUID.randomUUID()}.$extension",
            type = type,
            bytes = file.bytes,
            contentType = file.contentType.orEmpty()
        )
        repository.save(entity)
        val url = "${ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()}/uploads/${entity.name}"
        return ResponseEntity.ok(
            UploadResponse(
                name = entity.name,
                type = type,
                url = url
            )
        )
    }

    fun getFile(fileName: String): ResponseEntity<*> {
        val entity = repository.findById(fileName)
        return if (entity.isPresent) {
            ResponseEntity.status(HttpStatus.OK).header("Content-Type", entity.get().contentType)
                .body(entity.get().bytes)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    fun deleteFile(fileName: String): ResponseEntity<*> {
        return if (repository.existsById(fileName)) {
            repository.deleteById(fileName)
            ResponseEntity.ok().body(null)
        } else {
            ResponseEntity.badRequest().body(INCORRECT_FILE_NAME)
        }
    }
}