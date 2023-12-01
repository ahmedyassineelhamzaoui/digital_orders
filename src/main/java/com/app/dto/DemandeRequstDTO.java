package com.app.dto;

import com.app.models.Demande;
import com.app.models.Equipment;
import com.app.models.User;
import com.app.models.enums.DemandeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DemandeRequstDTO {
        private UUID userId;
        private UUID equipmentId ;
        private Date startDate;
        private Date endDate ;
        public Demande mapDemandeReqtoEntity(){
                return Demande.builder()
                        .user(User.builder().id(userId).build())
                        .equipment(Equipment.builder().id(equipmentId).build())
                        .startDate(startDate)
                        .endDate(endDate)
                        .build();
        }
}
