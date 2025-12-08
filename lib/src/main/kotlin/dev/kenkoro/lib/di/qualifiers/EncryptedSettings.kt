package dev.kenkoro.lib.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
annotation class EncryptedSettings
