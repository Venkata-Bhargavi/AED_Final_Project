/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Disease;

/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class Disease {
    
    private String diseaseName;
    private int diseaseId;
    private static int dis_count = 1;
    
    public Disease(){
        diseaseId = dis_count;
        dis_count++;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    @Override
    public String toString(){
        return diseaseName;
    }   
    
}
