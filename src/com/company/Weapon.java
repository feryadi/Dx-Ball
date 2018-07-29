package com.company;

import java.util.ArrayList;
import java.util.List;

public class Weapon extends ControlStick{

    static final List<Ball> ammoList = new ArrayList<>();

    public Weapon(int x, int y, int width, int height, boolean weapon, boolean grasp,boolean defence) {
        super(x, y, width, height, weapon, grasp,defence);
    }
}

