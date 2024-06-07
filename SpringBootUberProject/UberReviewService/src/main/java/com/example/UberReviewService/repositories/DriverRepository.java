package com.example.UberReviewService.repositories;

import com.example.UberReviewService.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

    @Query(nativeQuery = true, value = "SELECT * FROM driver WHERE id= :id AND license_number= :license")
    Optional<Driver> rawFindByIdAndLicenseNumber(Long id, String license);

    @Query("SELECT d FROM Driver as d WHERE d.id= :id AND d.licenseNumber= :lcns")
    Optional<Driver> hqlFindByIdAndLicense(Long id, String lcns);

    List<Driver> findAllByIdIn(List<Long> driverIds);
}
