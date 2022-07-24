package com.timothy.iline.data.utilities

import androidx.room.Room
import com.timothy.iline.IlineApp
import com.timothy.iline.data.local_storage.room.IlineDB


object StorageUtilities {
    val db = Room.databaseBuilder(
        IlineApp(),
        IlineDB::class.java,
        "IlineDB"
    )
}