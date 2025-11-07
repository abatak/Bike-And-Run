package com.batakantonio.bikeandrun.data.util

import androidx.room.Room
import com.batakantonio.bikeandrun.data.ChallengesRepositoryImpl
import com.batakantonio.bikeandrun.data.RecordRepository
import com.batakantonio.bikeandrun.data.database.BikeAndRunDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val testDatabaseModule = module {
    single {
        Room.inMemoryDatabaseBuilder(
            get(),
            BikeAndRunDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    // DAO
    single { get<BikeAndRunDatabase>().getRecordDao() }
    single(named("bikeTest")) { get<BikeAndRunDatabase>().getBikeChallengeDao() }
    single(named("runningTest")) { get<BikeAndRunDatabase>().getRunningChallengeDao() }


    // Repositories
    single { RecordRepository(get()) }
    single {
        ChallengesRepositoryImpl(
            get(named("bikeTest")),
            get(named("runningTest"))
        )
    }
}