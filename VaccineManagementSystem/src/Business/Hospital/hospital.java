/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Hospital;

/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class hospital {
    String name;
    int requestedQty;
    int usedQty;
    float vaccineUsage;
    float vaccineWaste;
    
    public float getVaccineWaste() {
        return vaccineWaste;
    }

    public void setVaccineWaste(float vaccineWaste) {
        this.vaccineWaste = vaccineWaste;
    }
    

    public float getVaccineUsage() {
        return vaccineUsage;
    }

    public void setVaccineUsage(float vaccineUsage) {
        this.vaccineUsage = vaccineUsage;
    }
    

    public int getUsedQty() {
        return usedQty;
    }

    public void setUsedQty(int usedQty) {
        this.usedQty = usedQty;
    }

    public int getRequestedQty() {
        return requestedQty;
    }

    public void setRequestedQty(int requestedQty) {
        this.requestedQty = requestedQty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
