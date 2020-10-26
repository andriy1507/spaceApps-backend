package com.spaceapps.backend.controllers

import com.spaceapps.backend.services.FilesService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.nio.file.Files

@RestController
@RequestMapping("files")
@Api(value = "Files Controller", tags = ["Files"], description = "Controller for upload/download files")
class FilesController @Autowired constructor(
        private val filesService: FilesService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Saves file and returns url", produces = "text/plain")
    fun uploadFile(@RequestParam file: MultipartFile): String {
        val fileName = filesService.saveFile(file)
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileName)
                .toUriString()
    }

    @GetMapping("/{filename}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns file with name as path-variable")
    fun downloadFile(@PathVariable filename: String): ResponseEntity<ByteArray> {
        val file = filesService.getFile(filename)
        val type = Files.probeContentType(file.toPath())
        return ResponseEntity
                .ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(type))
                .body(file.readBytes())

    }
}