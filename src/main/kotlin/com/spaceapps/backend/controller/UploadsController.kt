package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.uploads.UploadType
import com.spaceapps.backend.security.UploadsService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
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
        dataTypeClass = String::class,
        required = true
    )
    fun getFile(@PathVariable("fileName") fileName: String): ResponseEntity<*> {
        return uploadsService.getFile(fileName)
    }

    @PostMapping("/file")
    @ApiOperation("Uploads file")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun uploadFile(@RequestPart(required = true) file: MultipartFile): ResponseEntity<*> {
        return uploadsService.saveFile(file, UploadType.File)
    }

    @PostMapping("/image")
    @ApiOperation("Uploads image")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun uploadImage(@RequestPart(required = true) file: MultipartFile): ResponseEntity<*> {
        return uploadsService.saveFile(file, UploadType.Image)
    }

    @PostMapping("/video")
    @ApiOperation("Uploads video")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun uploadVideo(@RequestPart(required = true) file: MultipartFile): ResponseEntity<*> {
        return uploadsService.saveFile(file, UploadType.Video)
    }

    @PostMapping("/audio")
    @ApiOperation("Uploads audio")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun uploadAudio(@RequestPart(required = true) file: MultipartFile): ResponseEntity<*> {
        return uploadsService.saveFile(file, UploadType.Audio)
    }

    @DeleteMapping("/delete/{fileName}")
    @ApiOperation("Deletes file by name")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun deleteFile(@PathVariable("fileName") fileName: String): ResponseEntity<*> {
        return uploadsService.deleteFile(fileName)
    }
}