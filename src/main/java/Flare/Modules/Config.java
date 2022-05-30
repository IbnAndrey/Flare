package Flare.Modules;

import java.awt.*;
import java.io.Serializable;
import lombok.*;

@Data
@Builder
@Getter

public class Config implements Serializable {


     private boolean dateCheckBox,datePeriodCheckBox,qualityCheckBox,bloomCheckBox,seriaCheckBox;
     private Color chemsButton;
}
