package org.example.ecom2.repository;

import org.example.ecom2.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShipmentRepository extends JpaRepository<Shipment, Long> {}
