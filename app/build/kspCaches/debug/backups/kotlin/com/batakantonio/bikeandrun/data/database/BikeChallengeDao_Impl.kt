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
public class BikeChallengeDao_Impl(
  __db: RoomDatabase,
) : BikeChallengeDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfBikeChallenges: EntityInsertAdapter<BikeChallenges>

  private val __updateAdapterOfBikeChallenges: EntityDeleteOrUpdateAdapter<BikeChallenges>
  init {
    this.__db = __db
    this.__insertAdapterOfBikeChallenges = object : EntityInsertAdapter<BikeChallenges>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `bike_challenges` (`challengeId`,`title`,`isChecked`) VALUES (nullif(?, 0),?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BikeChallenges) {
        statement.bindLong(1, entity.challengeId.toLong())
        statement.bindText(2, entity.title)
        val _tmp: Int = if (entity.isChecked) 1 else 0
        statement.bindLong(3, _tmp.toLong())
      }
    }
    this.__updateAdapterOfBikeChallenges = object : EntityDeleteOrUpdateAdapter<BikeChallenges>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `bike_challenges` SET `challengeId` = ?,`title` = ?,`isChecked` = ? WHERE `challengeId` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: BikeChallenges) {
        statement.bindLong(1, entity.challengeId.toLong())
        statement.bindText(2, entity.title)
        val _tmp: Int = if (entity.isChecked) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindLong(4, entity.challengeId.toLong())
      }
    }
  }

  public override suspend fun createBikeChallenge(bikeChallenges: List<BikeChallenges>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfBikeChallenges.insert(_connection, bikeChallenges)
  }

  public override suspend fun updateBikeChallenge(bikeChallenges: List<BikeChallenges>): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfBikeChallenges.handleMultiple(_connection, bikeChallenges)
  }

  public override fun getAllBikeChallenges(): Flow<List<BikeChallenges>> {
    val _sql: String = "SELECT * FROM bike_challenges"
    return createFlow(__db, false, arrayOf("bike_challenges")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfChallengeId: Int = getColumnIndexOrThrow(_stmt, "challengeId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfIsChecked: Int = getColumnIndexOrThrow(_stmt, "isChecked")
        val _result: MutableList<BikeChallenges> = mutableListOf()
        while (_stmt.step()) {
          val _item: BikeChallenges
          val _tmpChallengeId: Int
          _tmpChallengeId = _stmt.getLong(_columnIndexOfChallengeId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpIsChecked: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsChecked).toInt()
          _tmpIsChecked = _tmp != 0
          _item = BikeChallenges(_tmpChallengeId,_tmpTitle,_tmpIsChecked)
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
