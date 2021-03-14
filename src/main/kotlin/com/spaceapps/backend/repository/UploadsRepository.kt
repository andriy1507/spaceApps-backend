package com.spaceapps.backend.repository

import com.spaceapps.backend.model.dao.uploads.UploadEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UploadsRepository : CrudRepository<UploadEntity, String> {
}