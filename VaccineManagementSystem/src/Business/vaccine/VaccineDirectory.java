/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.vaccine;

import java.util.ArrayList;


/**
 *
 * @author Krishnakanth Naik Jarapala, Venkata Bhargavi Sikhakolli.
 */


public class VaccineDirectory {
    
    ArrayList<Vaccine> vaccineList;
    
    public VaccineDirectory(){
        vaccineList = new ArrayList();
    }
    public ArrayList<Vaccine> getVaccineList() {
        return vaccineList;
    }

    public void setVaccineList(ArrayList<Vaccine> vaccineList) {
        this.vaccineList = vaccineList;
    }
    
    public Vaccine addVaccine() {
        Vaccine vaccine = new Vaccine();
        vaccineList.add(vaccine);
        return vaccine;
    }
    
    //Removing an vaccine from the Directory
    public void deleteVaccine(Vaccine vaccine) {
        vaccineList.remove(vaccine);
    }
    
    
    
}
