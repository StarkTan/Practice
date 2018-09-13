package com.stark.quatz.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDevice extends BaseEntity {

    private int rate; //采集间隔 ms


}
