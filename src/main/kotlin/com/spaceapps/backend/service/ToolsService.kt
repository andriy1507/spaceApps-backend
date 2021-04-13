package com.spaceapps.backend.service

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.spaceapps.backend.model.dto.tools.MetaDataResponse
import org.jsoup.Jsoup
import org.jsoup.parser.Parser
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.util.*
import javax.imageio.ImageIO

@Service
class ToolsService {

    fun parseLinkMetaData(link: String): MetaDataResponse {
        val html = Jsoup.connect(link).parser(Parser.htmlParser()).get().head()
        val name = html.select("meta[property$=title], meta[name$=title], meta[property$=site_name]").attr("content")
        val url = html.select("meta[property$=url], meta[name$=url]").attr("content")
        val image = html.select("meta[property$=image], meta[name$=image]").attr("content")
        val description = html.select("meta[property$=description], meta[name$=description]").attr("content")
        return MetaDataResponse(name, description, url, image)
    }

    fun generateQrCode(data: String, width: Int, height: Int): String {
        val bitMatrix = QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, width, height)
        val outputStream = ByteArrayOutputStream()
        val image = MatrixToImageWriter.toBufferedImage(bitMatrix)
        ImageIO.write(image, "png", outputStream)
        return Base64.getEncoder().encodeToString(outputStream.toByteArray())
    }

}