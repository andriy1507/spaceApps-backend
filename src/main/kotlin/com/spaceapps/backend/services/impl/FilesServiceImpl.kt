package com.spaceapps.backend.services.impl

import com.spaceapps.backend.services.FilesService
import org.springframework.core.io.FileUrlResource
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.File
import java.util.*
import javax.annotation.PostConstruct

@Service
class FilesServiceImpl : FilesService {

    private val dir = File("files")

    @PostConstruct
    fun init() {
        if (!dir.exists())
            dir.mkdir()
    }

    override fun saveFile(file: MultipartFile): String {
        val extension = file.originalFilename?.substringAfterLast(".")
        val name = "${UUID.randomUUID()}.$extension"
        val local = File(dir, name)
        local.createNewFile()
        file.inputStream.copyTo(local.outputStream())
        return name
    }

    override fun getFile(name: String): File {
        return File(dir, name)
    }


}