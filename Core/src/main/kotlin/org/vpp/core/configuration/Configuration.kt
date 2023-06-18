package org.vpp.core.configuration

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.vpp.adapter.configuration.AdapterConfiguration
import org.vpp.internalApi.configuration.InternalApiConfiguration
import org.vpp.storage.configuration.StorageConfiguration

@Configuration
@EnableScheduling
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableConfigurationProperties
@Import(
    value = [
        AdapterConfiguration::class,
        StorageConfiguration::class,
        InternalApiConfiguration::class
    ]
)
class Configuration
