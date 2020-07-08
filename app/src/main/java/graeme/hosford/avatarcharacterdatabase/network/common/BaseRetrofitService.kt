package graeme.hosford.avatarcharacterdatabase.network.common

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Base class to  be used in all retrofit services.
 *
 * Handles getting an instance of a service for network communication.
 */
abstract class BaseRetrofitService<Service> {

    val service: Service by lazy { createService() }

    private fun createService(): Service {
        return Retrofit.Builder()
            .baseUrl("https://last-airbender-api.herokuapp.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(getServiceClass())
    }

    abstract fun getServiceClass(): Class<Service>
}