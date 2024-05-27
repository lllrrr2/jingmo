package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.PoemSentenceCollectionEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PoemSentenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PoemSentenceEntity)

    @Query("select p.* from poem_sentences p where p.id = :id")
    fun getSentence(id: Int): Flow<PoemSentenceEntity>

    @Query("select p.* from poem_sentences p where p.id = (select id from poem_sentences order by random() limit 1)")
    fun random(): Flow<PoemSentenceEntity>

    @Query("select id from poem_sentences where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from poem_sentences where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from poem_sentences where content like :query")
    fun searchSentencesList(query: String): PagingSource<Int, PoemSentenceEntity>

    @Query("select p.* from poem_sentence_collections c join poem_sentences p on c.id = p.id  order by c.collected_at desc")
    fun collections(): PagingSource<Int, PoemSentenceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: PoemSentenceCollectionEntity)

    @Query("delete from poem_sentence_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("select * from poem_sentence_collections where id = :id")
    fun isCollect(id: Int): Flow<PoemSentenceCollectionEntity?>


    @Query("select count(*) from poem_sentences")
    fun total(): Flow<Int>
}