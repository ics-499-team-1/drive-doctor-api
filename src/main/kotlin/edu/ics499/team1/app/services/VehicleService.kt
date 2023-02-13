package edu.ics499.team1.app.services

import edu.ics499.team1.app.repositiories.VehicleDataSource
import org.springframework.stereotype.Service

@Service
class VehicleService(private val dataSource: VehicleDataSource) {

}