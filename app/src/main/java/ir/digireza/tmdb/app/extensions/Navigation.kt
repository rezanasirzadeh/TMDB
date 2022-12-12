package ir.digireza.tmdb.app.extensions

import android.content.Context
import android.content.Intent


inline fun <reified T> Context.navigation(extras: Map<String, String>): Intent =
    Intent(this, T::class.java).apply {
        extras.forEach { (key, value) -> this.putExtra(key, value) }
    }


inline fun <reified T : Enum<T>> Intent.putEnumExtra(enum: T): Intent =
    putExtra(T::class.java.name, enum.ordinal)

inline fun <reified T: Enum<T>> Intent.getEnumExtra(): T? =
    getIntExtra(T::class.java.name, -1)
        .takeUnless {
            it == -1
        }?.let { T::class.java.enumConstants?.get(it) }

