package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.FtsOptions
import androidx.room.PrimaryKey

@Fts4(
    tokenizer = FtsOptions.TOKENIZER_ICU,
    contentEntity = ClassicPoemEntity::class,
)
@Entity(tableName = "classic_poems_fts")
data class ClassicPoemFtsEntity(
    @PrimaryKey
    @ColumnInfo("rowid")
    val id: Int,
    val writer: String,
    val title: String,
    val content: String,
)
