package com.batakantonio.bikeandrun.`data`.database

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class BikeAndRunDatabase_Impl : BikeAndRunDatabase() {
  private val _recordDao: Lazy<RecordDao> = lazy {
    RecordDao_Impl(this)
  }

  private val _bikeChallengeDao: Lazy<BikeChallengeDao> = lazy {
    BikeChallengeDao_Impl(this)
  }

  private val _runningChallengeDao: Lazy<RunningChallengeDao> = lazy {
    RunningChallengeDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(3, "9397b461e4e961cb776d50adab8b8f00", "c63b37ce25034b36de94b6a529b47e68") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `record` (`recordId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `time` TEXT, `isChecked` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `bike_challenges` (`challengeId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `isChecked` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `running_challenges` (`challengeId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `isChecked` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9397b461e4e961cb776d50adab8b8f00')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `record`")
        connection.execSQL("DROP TABLE IF EXISTS `bike_challenges`")
        connection.execSQL("DROP TABLE IF EXISTS `running_challenges`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsRecord: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRecord.put("recordId", TableInfo.Column("recordId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecord.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecord.put("time", TableInfo.Column("time", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecord.put("isChecked", TableInfo.Column("isChecked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRecord: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRecord: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRecord: TableInfo = TableInfo("record", _columnsRecord, _foreignKeysRecord, _indicesRecord)
        val _existingRecord: TableInfo = read(connection, "record")
        if (!_infoRecord.equals(_existingRecord)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |record(com.batakantonio.bikeandrun.data.database.Record).
              | Expected:
              |""".trimMargin() + _infoRecord + """
              |
              | Found:
              |""".trimMargin() + _existingRecord)
        }
        val _columnsBikeChallenges: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBikeChallenges.put("challengeId", TableInfo.Column("challengeId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBikeChallenges.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBikeChallenges.put("isChecked", TableInfo.Column("isChecked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBikeChallenges: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBikeChallenges: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBikeChallenges: TableInfo = TableInfo("bike_challenges", _columnsBikeChallenges, _foreignKeysBikeChallenges, _indicesBikeChallenges)
        val _existingBikeChallenges: TableInfo = read(connection, "bike_challenges")
        if (!_infoBikeChallenges.equals(_existingBikeChallenges)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |bike_challenges(com.batakantonio.bikeandrun.data.database.BikeChallenges).
              | Expected:
              |""".trimMargin() + _infoBikeChallenges + """
              |
              | Found:
              |""".trimMargin() + _existingBikeChallenges)
        }
        val _columnsRunningChallenges: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRunningChallenges.put("challengeId", TableInfo.Column("challengeId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRunningChallenges.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRunningChallenges.put("isChecked", TableInfo.Column("isChecked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRunningChallenges: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRunningChallenges: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRunningChallenges: TableInfo = TableInfo("running_challenges", _columnsRunningChallenges, _foreignKeysRunningChallenges, _indicesRunningChallenges)
        val _existingRunningChallenges: TableInfo = read(connection, "running_challenges")
        if (!_infoRunningChallenges.equals(_existingRunningChallenges)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |running_challenges(com.batakantonio.bikeandrun.data.database.RunningChallenges).
              | Expected:
              |""".trimMargin() + _infoRunningChallenges + """
              |
              | Found:
              |""".trimMargin() + _existingRunningChallenges)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "record", "bike_challenges", "running_challenges")
  }

  public override fun clearAllTables() {
    super.performClear(false, "record", "bike_challenges", "running_challenges")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(RecordDao::class, RecordDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(BikeChallengeDao::class, BikeChallengeDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RunningChallengeDao::class, RunningChallengeDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun getRecordDao(): RecordDao = _recordDao.value

  public override fun getBikeChallengeDao(): BikeChallengeDao = _bikeChallengeDao.value

  public override fun getRunningChallengeDao(): RunningChallengeDao = _runningChallengeDao.value
}
