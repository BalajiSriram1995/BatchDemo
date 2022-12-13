package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NBADTO {

    private String Name;

    private String Team;

    private String Position;

    private NBAStats nbastats;

}
