package com.spaceapps.backend.services

import org.springframework.web.multipart.MultipartFile
import java.io.File

interface FilesService {

    fun saveFile(file: MultipartFile): String

    fun getFile(name: String): File
}