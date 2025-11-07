package com.batakantonio.bikeandrun.`data`.database

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RecordDao_Impl(
  __db: RoomDatabase,
) : RecordDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRecord: EntityInsertAdapter<Record>

  private val __deleteAdapterOfRecord: EntityDeleteOrUpdateAdapter<Record>

  private val __updateAdapterOfRecord: EntityDeleteOrUpdateAdapter<Record>
  init {
    this.__db = __db
    this.__insertAdapterOfRecord = object : EntityInsertAdapter<Record>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `record` (`recordId`,`title`,`time`,`isChecked`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Record) {
        statement.bindLong(1, entity.recordId.toLong())
        statement.bindText(2, entity.title)
        val _tmpTime: String? = entity.time
        if (_tmpTime == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpTime)
        }
        val _tmp: Int = if (entity.isChecked) 1 else 0
        statement.bindLong(4, _tmp.toLong())
      }
    }
    this.__deleteAdapterOfRecord = object : EntityDeleteOrUpdateAdapter<Record>() {
      protected override fun createQuery(): String = "DELETE FROM `record` WHERE `recordId` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Record) {
        statement.bindLong(1, entity.recordId.toLong())
      }
    }
    this.__updateAdapterOfRecord = object : EntityDeleteOrUpdateAdapter<Record>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `record` SET `recordId` = ?,`title` = ?,`time` = ?,`isChecked` = ? WHERE `recordId` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Record) {
        statement.bindLong(1, entity.recordId.toLong())
        statement.bindText(2, entity.title)
        val _tmpTime: String? = entity.time
        if (_tmpTime == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpTime)
        }
        val _tmp: Int = if (entity.isChecked) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        statement.bindLong(5, entity.recordId.toLong())
      }
    }
  }

  public override suspend fun createRecord(record: Record): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRecord.insert(_connection, record)
  }

  public override suspend fun deleteRecord(record: Record): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfRecord.handle(_connection, record)
  }

  public override suspend fun updateRecord(record: Record): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfRecord.handle(_connection, record)
  }

  public override fun getAllRecords(): Flow<List<Record>> {
    val _sql: String = "SELECT * FROM record"
    return createFlow(__db, false, arrayOf("record")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfRecordId: Int = getColumnIndexOrThrow(_stmt, "recordId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfTime: Int = getColumnIndexOrThrow(_stmt, "time")
        val _columnIndexOfIsChecked: Int = getColumnIndexOrThrow(_stmt, "isChecked")
        val _result: MutableList<Record> = mutableListOf()
        while (_stmt.step()) {
          val _item: Record
          val _tmpRecordId: Int
          _tmpRecordId = _stmt.getLong(_columnIndexOfRecordId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpTime: String?
          if (_stmt.isNull(_columnIndexOfTime)) {
            _tmpTime = null
          } else {
            _tmpTime = _stmt.getText(_columnIndexOfTime)
          }
          val _tmpIsChecked: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsChecked).toInt()
          _tmpIsChecked = _tmp != 0
          _item = Record(_tmpRecordId,_tmpTitle,_tmpTime,_tmpIsChecked)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAllRecord() {
    val _sql: String = "DELETE FROM record"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
