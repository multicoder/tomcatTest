/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;

//import javax.faces.view.ViewScoped;
//import javax.faces.bean.ViewScoped;
//import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sb.boundary.SampleBoundary;
import sb.entity.Car;
import sb.entity.DummyCar;

/**
 *
 * @author multicoder
 */
//Tomcat don't support these?
@Named(value = "sampleView")
//@ViewScoped
//https://stackoverflow.com/questions/2012085/jsf-accessing-a-sessionscoped-managed-bean
//@ManagedBean(name="sampleView")
@ViewScoped

public class SampleView implements Serializable {
    
    @Inject
    SampleBoundary sampleBoundary;
    //SampleBoundary sampleBoundary = new SampleBoundary();
    
    List<DummyCar> dummyCars = null;

    //https://www.primefaces.org/showcase/ui/data/datalist/basic.xhtml?jfwid=566db
    //https://stackoverflow.com/questions/17587304/how-to-use-if-else-condition-in-jsf-to-display-image#22806817
    //https://stackoverflow.com/questions/25055016/does-jsf-manage-access-authorization-for-managedbean-methods
    
    public List<DummyCar> getDummyCars() {
        if(dummyCars == null){
            if(sampleBoundary == null){
                dummyCars = new ArrayList<>();
                DummyCar dc = new DummyCar();
                dc.setCarId(1234);
                dc.setMake("The Injection failed! for getDummyCars()");
                dc.setModel("Buttered Toast put back in the toaster! :(");
                dc.setYear(-1000);
                dummyCars.add(dc);
            }else{
                dummyCars = sampleBoundary.getDummyCars();
            }
        }
        return dummyCars;
    }

    public void setDummyCars(List<DummyCar> dummyCars) {
        this.dummyCars = dummyCars;
    }
    
    List<Car> Cars = null;

    public List<Car> getCars() {
        if(Cars == null){
            if(sampleBoundary == null){
                Cars = new ArrayList<>();
                Car tCar = new Car();
                tCar.setCarId(1234);
                tCar.setMake("The Injection failed! for getCars()");
                tCar.setModel("Buttered Toast put back in the toaster! :(");
                tCar.setYear(-1000);
                Cars.add(tCar);
            }else{
                Cars = sampleBoundary.getCars();
            }
        }
        return Cars;
    }

    public void setCars(List<Car> Cars) {
        this.Cars = Cars;
    }
    
    public void createCar(){
        sampleBoundary.createSampleCars();
    }
}
