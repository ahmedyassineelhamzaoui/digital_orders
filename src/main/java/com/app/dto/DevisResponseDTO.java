package com.app.dto;

import java.util.List;
import com.app.models.Demande;
import com.app.models.enums.DevisStatus;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;



@Builder
@Data
public class DevisResponseDTO {
    private String Terms;
    private DevisStatus devisStatus;
    private List<DemandeRequstDTO> demandes;


}
