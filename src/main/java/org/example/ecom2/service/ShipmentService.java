package org.example.ecom2.service;

import org.example.ecom2.model.Shipment;
import org.example.ecom2.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

}
