package io.github.ovso.herotest

import android.app.Application
import androidx.room.Room
import io.github.ovso.herotest.data.ServiceLocator
import io.github.ovso.herotest.data.TasksRepository
import io.github.ovso.herotest.data.local.AppDatabase
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

    val taskRepository: TasksRepository
        get() = ServiceLocator.provideTasksRepository(this)

    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database").build()
    }
}