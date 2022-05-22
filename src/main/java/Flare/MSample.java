package Flare;
import java.io.Serializable;

import lombok.*;

@Data
@Builder
public class MSample implements Serializable {
    private String date,time,sampleNo,quality,bloom, codes,seria,C,Si,Mn,P,S,Cr,Ni,Mo,Cu,Al,As,V,W,Ti;
}
