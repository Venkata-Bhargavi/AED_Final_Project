# Vaccine Inventory and Distribution Monitoring

## Abstract
A vaccine management system which combinely manages distribution, inventory and monitoring of vaccine supply. It ensures a good governance of vaccine management and provides transparency of vaccine distribution and its usage in whole supply chain right from the manufactures to the individual. This applications provides the traceability and security of the vaccines and its recipients. This system can be expanded to any other types of vaccines and drugs in the future.

Important enterprises includes Center for Disease Control and Prevention (CDC), Public Health Department(PHD), Distributor, Vaccine supplier, Hospital and Insurance

## Main Functionalities

- PHD is responsible for approving new hospitals and also authorizing the vaccine requests by the hospitals
- CDC is responsible for authorizing request forwarded by PHD and also creating champaigns to increase the reach of vaccine to each individual
- Distributor is responsible for distributing the vaccines to hospitals , clinics ,pahrmacy's
- Supplier is responsible for mamnufacturing vaccine and supplying to the distributor
- Hospitals are resposible for prescribing a required vaccine for the patient and pharmacy's are responsible for providing the requested vaccine by doctor
- Insurance company's are responsible for paying the vaccine cost for each insured individual

## End User

A private management who takes responsibilities of distributing and managing vaccine supply chain, Pharmacy, Hospital, Volunteers or The Govenrnment itself.

## DataBase

The database system used in this application is Db4o (o for object) an embeddable open-source object database for Java. User can add the required Jar files in library and connect the databank to store the objects


## High Level Design 

![VMS_Flowchart](https://user-images.githubusercontent.com/26923878/204704414-876dfebe-f77c-4886-881a-0ff3b3c5f7ef.jpg)

## Object Diagram

![image](https://user-images.githubusercontent.com/114631063/206959380-b35188da-d535-4f5f-9138-31424295bd11.png)

## Class Diagram

![image](https://user-images.githubusercontent.com/114631063/206963450-eecfd261-66fd-4741-9c3a-9ff1df545bce.png)



## User Documentation

- Clone repository using `git clone "https://github.com/Venkata-Bhargavi/AED_Final_Project.git"`
- pull the main branch for updated project
- Place Db4o , Javax-email, jfreechart, activation, and supported JDK in the libraries (This project supports and runs on JDK version 11)
- Clean build and Run MainLoginJFrame.Java in Package UI



