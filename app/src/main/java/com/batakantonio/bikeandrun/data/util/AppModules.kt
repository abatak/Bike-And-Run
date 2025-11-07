package com.batakantonio.bikeandrun.data.util

import androidx.room.Room
import com.batakantonio.bikeandrun.data.ChallengesRepositoryImpl
import com.batakantonio.bikeandrun.data.RecordRepository
import com.batakantonio.bikeandrun.data.database.BikeAndRunDatabase
import com.batakantonio.bikeandrun.data.database.BikeAndRunDatabase.Companion.MIGRATION_2_TO_3
import com.batakantonio.bikeandrun.data.model.BikeChallengeViewModel
import com.batakantonio.bikeandrun.data.model.EditRecordsViewModel
import com.batakantonio.bikeandrun.data.model.RunningChallengeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
             androidContext(),
            BikeAndRunDatabase::class.java,
            "bike-and-run-database"
        )
            .addMigrations(MIGRATION_2_TO_3)
            .build()
    }

    // DAO
    single { get<BikeAndRunDatabase>().getRecordDao() }
    single(named("bike")) { get<BikeAndRunDatabase>().getBikeChallengeDao() }
    single(named("running")) { get<BikeAndRunDatabase>().getRunningChallengeDao() }
}

val repositoryModule = module {
    single { RecordRepository(get()) }
    single { ChallengesRepositoryImpl(get(named("bike")),
        get(named("running"))) }
}

val viewModelModule = module {
    viewModel { EditRecordsViewModel(get()) }
    viewModel { BikeChallengeViewModel(get( )) }
    viewModel { RunningChallengeViewModel(get()) }
}




