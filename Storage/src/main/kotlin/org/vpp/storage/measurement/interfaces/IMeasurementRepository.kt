package org.vpp.storage.measurement.interfaces

import org.springframework.stereotype.Service
import org.vpp.storage.framework.ICrudlRepository
import org.vpp.storage.measurement.MeasurementEntity

@Service
interface IMeasurementRepository : ICrudlRepository<MeasurementEntity>
