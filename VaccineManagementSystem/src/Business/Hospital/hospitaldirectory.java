/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Hospital;

import java.util.ArrayList;

/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class hospitaldirectory {
    
    ArrayList<hospital> hosList;

    public ArrayList<hospital> getHosList() {
        return hosList;
    }

    public void setHosList(ArrayList<hospital> hosList) {
        this.hosList = hosList;
    }

    public hospitaldirectory() {
        hosList = new ArrayList();
    }
    
    public hospital addHospital()
    {
        hospital hos = new hospital();
        hosList.add(hos);
        return hos;
    }
}
