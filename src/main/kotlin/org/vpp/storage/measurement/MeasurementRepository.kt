package org.vpp.storage.measurement

import org.vpp.storage.framework.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.storage.measurement.interfaces.IMeasurementRepository

@Service
class MeasurementRepository @Autowired constructor(
    repository: IMeasurementRepository
) : Repository<MeasurementEntity>(repository = repository)
