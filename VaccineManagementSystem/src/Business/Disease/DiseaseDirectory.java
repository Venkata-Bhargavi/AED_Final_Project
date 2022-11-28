/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Disease;

import java.util.ArrayList;

/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class DiseaseDirectory {
    ArrayList<Disease> diseaselist;
    
    public DiseaseDirectory(){
        
        diseaselist = new ArrayList();
    }
    
    public ArrayList<Disease> getDiseaselist() {
        return diseaselist;
    }

    public void setDiseaselist(ArrayList<Disease> diseaselist) {
        this.diseaselist = diseaselist;
    }
    
    public Disease addDisease() {
        Disease dis = new Disease();
        diseaselist.add(dis);
        return dis;
    }
    
    public void removeDisease(Disease dis) {
        diseaselist.remove(dis);
    }
}
