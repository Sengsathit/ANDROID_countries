package kodizfun.countries.di

import javax.inject.Scope

/**
 * Custom scope for global injection with more explicit name than
 * the predefined scope @Singleton which is misleading name
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope