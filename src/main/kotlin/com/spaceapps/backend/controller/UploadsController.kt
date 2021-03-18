package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.uploads.UploadResponse
import com.spaceapps.backend.model.dto.uploads.UploadType
import com.spaceapps.backend.service.UploadsService
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@Api(tags = ["Uploads"], description = "Uploads endpoints")
@RequestMapping("uploads")
class UploadsController @Autowired constructor(
    private val uploadsService: UploadsService
) {

    @GetMapping("/{fileName}")
    @ApiOperation("Returns file by name")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun getFile(@PathVariable("fileName") fileName: String): ResponseEntity<*> {
        return uploadsService.getFile(fileName)
    }

    @PostMapping("/file")
    @ApiOperation("Uploads file")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = UploadResponse::class))
    fun uploadFile(@RequestPart(required = true) file: MultipartFile): ResponseEntity<*> {
        return uploadsService.saveFile(file, UploadType.File)
    }

    @PostMapping("/image")
    @ApiOperation("Uploads image")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = UploadResponse::class))
    fun uploadImage(@RequestPart(required = true) file: MultipartFile): ResponseEntity<*> {
        return uploadsService.saveFile(file, UploadType.Image)
    }

    @PostMapping("/video")
    @ApiOperation("Uploads video")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = UploadResponse::class))
    fun uploadVideo(@RequestPart(required = true) file: MultipartFile): ResponseEntity<*> {
        return uploadsService.saveFile(file, UploadType.Video)
    }

    @PostMapping("/audio")
    @ApiOperation("Uploads audio")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = UploadResponse::class))
    fun uploadAudio(@RequestPart(required = true) file: MultipartFile): ResponseEntity<*> {
        return uploadsService.saveFile(file, UploadType.Audio)
    }

    @DeleteMapping("/{fileName}")
    @ApiOperation("Deletes file by name")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun deleteFile(@PathVariable("fileName") fileName: String): ResponseEntity<*> {
        return uploadsService.deleteFile(fileName)
    }
}