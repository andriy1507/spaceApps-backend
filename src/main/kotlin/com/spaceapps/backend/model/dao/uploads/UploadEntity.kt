package com.spaceapps.backend.model.dao.uploads

import com.spaceapps.backend.model.dto.uploads.UploadType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "files")
class UploadEntity(
    @Id
    @Column(name = "id")
    var name: String = "",
    @Column(name = "type")
    var type: UploadType = UploadType.File,
    @Column(name = "bytes")
    var bytes: ByteArray = byteArrayOf(),
    @Column(name = "content_type")
    var contentType: String = ""
)