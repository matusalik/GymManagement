package com.gymmanagement.gym_management_api.Entities;
import com.gymmanagement.gym_management_api.Enums.EquipmentAvailability;
import com.gymmanagement.gym_management_api.Enums.EquipmentCondition;
import com.gymmanagement.gym_management_api.Enums.EquipmentLocation;
import com.gymmanagement.gym_management_api.Enums.EquipmentType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private Integer equipmentId;

    private String name;

    @Enumerated(EnumType.STRING)
    private EquipmentType equipment_type;

    @Enumerated(EnumType.STRING)
    private EquipmentLocation equipment_location;

    @Enumerated(EnumType.STRING)
    private EquipmentCondition equipment_condition;

    @Enumerated(EnumType.STRING)
    private EquipmentAvailability equipment_availability;
}
