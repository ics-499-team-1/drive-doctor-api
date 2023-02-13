package edu.ics499.team1.app.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.io.Serializable

@Repository
interface DriveDoctorRepository <T, ID : Serializable> : JpaRepository<T, ID>