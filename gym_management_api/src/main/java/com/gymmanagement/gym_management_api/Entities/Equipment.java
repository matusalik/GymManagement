package com.gymmanagement.gym_management_api.Entities;
import com.gymmanagement.gym_management_api.Enums.EquipmentAvailability;
import com.gymmanagement.gym_management_api.Enums.EquipmentCondition;
import com.gymmanagement.gym_management_api.Enums.EquipmentLocation;
import com.gymmanagement.gym_management_api.Enums.EquipmentType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer equipmentId;

    String name;

    EquipmentType equipment_type;

    EquipmentLocation equipment_location;

    EquipmentCondition equipment_condition;

    EquipmentAvailability equipment_availability;
}
