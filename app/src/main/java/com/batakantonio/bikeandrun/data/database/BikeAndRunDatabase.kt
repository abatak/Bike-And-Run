package com.batakantonio.bikeandrun.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database([Record::class, BikeChallenges::class, RunningChallenges::class], version = 3, exportSchema = false)

abstract class BikeAndRunDatabase : RoomDatabase() {

    abstract fun getRecordDao(): RecordDao

    abstract fun getBikeChallengeDao(): BikeChallengeDao

    abstract fun getRunningChallengeDao(): RunningChallengeDao


    companion object {

        val MIGRATION_2_TO_3 = object : Migration(2, 3) {

            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """CREATE TABLE IF NOT EXISTS 'bike_challenges'
                    'challengeId' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    'title' TEXT NOT NULL,
                    'checkedId' INTEGER NOT NULL""".trimIndent()
                )

                db.execSQL(
                    """CREATE TABLE IF NOT EXISTS 'running_challenges'
                    'challengeId' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    'title' TEXT NOT NULL,
                    'checkedId' INTEGER NOT NULL""".trimIndent()
                )
            }
        }
    }
}


