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
public class RunningChallengeDao_Impl(
  __db: RoomDatabase,
) : RunningChallengeDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRunningChallenges: EntityInsertAdapter<RunningChallenges>

  private val __updateAdapterOfRunningChallenges: EntityDeleteOrUpdateAdapter<RunningChallenges>
  init {
    this.__db = __db
    this.__insertAdapterOfRunningChallenges = object : EntityInsertAdapter<RunningChallenges>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `running_challenges` (`challengeId`,`title`,`isChecked`) VALUES (nullif(?, 0),?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RunningChallenges) {
        statement.bindLong(1, entity.challengeId.toLong())
        statement.bindText(2, entity.title)
        val _tmp: Int = if (entity.isChecked) 1 else 0
        statement.bindLong(3, _tmp.toLong())
      }
    }
    this.__updateAdapterOfRunningChallenges = object : EntityDeleteOrUpdateAdapter<RunningChallenges>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `running_challenges` SET `challengeId` = ?,`title` = ?,`isChecked` = ? WHERE `challengeId` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RunningChallenges) {
        statement.bindLong(1, entity.challengeId.toLong())
        statement.bindText(2, entity.title)
        val _tmp: Int = if (entity.isChecked) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindLong(4, entity.challengeId.toLong())
      }
    }
  }

  public override suspend fun createRunningChallenge(runningChallenges: List<RunningChallenges>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRunningChallenges.insert(_connection, runningChallenges)
  }

  public override suspend fun updateRunningChallenge(runningChallenges: List<RunningChallenges>): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfRunningChallenges.handleMultiple(_connection, runningChallenges)
  }

  public override fun getAllRunningChallenges(): Flow<List<RunningChallenges>> {
    val _sql: String = "SELECT * FROM running_challenges"
    return createFlow(__db, false, arrayOf("running_challenges")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfChallengeId: Int = getColumnIndexOrThrow(_stmt, "challengeId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfIsChecked: Int = getColumnIndexOrThrow(_stmt, "isChecked")
        val _result: MutableList<RunningChallenges> = mutableListOf()
        while (_stmt.step()) {
          val _item: RunningChallenges
          val _tmpChallengeId: Int
          _tmpChallengeId = _stmt.getLong(_columnIndexOfChallengeId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpIsChecked: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsChecked).toInt()
          _tmpIsChecked = _tmp != 0
          _item = RunningChallenges(_tmpChallengeId,_tmpTitle,_tmpIsChecked)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
