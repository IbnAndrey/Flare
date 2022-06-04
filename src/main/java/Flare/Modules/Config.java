package Flare.Modules;

import java.awt.*;
import java.io.Serializable;
import lombok.*;

@Data
@Builder
@Getter

public class Config implements Serializable {


     private boolean dateCheckBox,datePeriodCheckBox,qualityCheckBox,bloomCheckBox,seriaCheckBox,samplesPerStepCheckBox;
     private Color chemsButton;
     String CDev,SiDev,MnDev,PDev,SDev,CrDev,NiDev,MoDev,CuDev,AlDev,VDev,WDev,TiDev;
     String CDevAuto,SiDevAuto,MnDevAuto,PDevAuto,SDevAuto,CrDevAuto,NiDevAuto,MoDevAuto,CuDevAuto,AlDevAuto,VDevAuto,WDevAuto,TiDevAuto;
     String CStepAuto,SiStepAuto,MnStepAuto,PStepAuto,SStepAuto,CrStepAuto,NiStepAuto,MoStepAuto,CuStepAuto,AlStepAuto,VStepAuto,WStepAuto,TiStepAuto,samplesPerStep;
}
