package com.spaceapps.backend.services

import java.io.File

interface MediaConverterService {

    fun convertImage(source: File): File

    fun convertVideo(source: File): File

    fun convertAudio(source: File): File

}