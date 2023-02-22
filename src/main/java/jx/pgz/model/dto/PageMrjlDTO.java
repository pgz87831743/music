package jx.pgz.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageMrjlDTO extends PageDTO{

    private String  name;
    private String dataStr;


}
