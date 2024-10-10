package catcheat.android.owner.data.prefs

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class DelegatedPreferences<T>(
    private val prefs: SharedPreferences,
    private val defaultValue: T
) : ReadWriteProperty<Any?, T> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return when (defaultValue) {
            is String -> prefs.getString(property.name, defaultValue) as T
            is Boolean -> prefs.getBoolean(property.name, defaultValue) as T
            is Int -> prefs.getInt(property.name, defaultValue) as T
            is Float -> prefs.getFloat(property.name, defaultValue) as T
            is Long -> prefs.getLong(property.name, defaultValue) as T
            else -> throw IllegalArgumentException("Unsupported type.")
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        with(prefs.edit()) {
            when (value) {
                is String -> putString(property.name, value)
                is Boolean -> putBoolean(property.name, value)
                is Int -> putInt(property.name, value)
                is Float -> putFloat(property.name, value)
                is Long -> putLong(property.name, value)
                else -> throw IllegalArgumentException("Unsupported type.")
            }
            apply()
        }
    }
}
