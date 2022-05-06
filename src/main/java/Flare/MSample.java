package Flare;
import java.io.Serializable;

import lombok.*;

@Data
@Builder
public class MSample implements Serializable {
    private String sampleNo,bloom, quality,cod_1,cod_2,cod_3,cod_4,C,Si,Mn,P,S,Cr,Ni,Mo,Cu,Al,V,W,Ti;
}
