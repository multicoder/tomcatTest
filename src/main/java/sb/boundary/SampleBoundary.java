/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sb.entity.Car;
import sb.entity.DummyCar;

/**
 *
 * @author multicoder
 */
@Stateless
//Don't forget to put a persistance context
//https://developer.jboss.org/thread/223578

public class SampleBoundary implements Serializable {

    //@Inject
    @PersistenceContext(unitName = "SamplePU")
    private EntityManager em;

    /**
     * This method is here to return a dummy set of data, this does not require
     * a working persistence unit
     *
     * @return
     */
    public List<DummyCar> getDummyCars() {
        List<DummyCar> dummyCars = new ArrayList<>();
        DummyCar dc = new DummyCar();
        dc.setCarId(1);
        dc.setMake("Found On Road Dead (FORD)");
        dc.setModel("Vulture");
        dc.setYear(1977);
        dummyCars.add(dc);
        dc = new DummyCar();
        dc.setCarId(2);
        dc.setMake("Telsa Coil");
        dc.setModel("Down With Edison V2");
        dc.setYear(1931);
        dummyCars.add(dc);
        dc = new DummyCar();
        dc.setCarId(3);
        dc.setMake("Prius");
        dc.setModel("Maximum of the minimum legal size Mark IIIIIV");
        dc.setYear(2013);
        dummyCars.add(dc);
        return dummyCars;
    }

    public List<Car> getCars() {
        List<Car> cars = null;
        try {
            if (em == null) {
                cars = new ArrayList<>();
                Car car = new Car();
                car.setCarId(-1);
                car.setMake("getCars():Em is null error");
                car.setModel("getCars():We just have no entity manager to deal with");
                cars.add(car);
            }
            if(!em.isOpen()){
                cars = new ArrayList<>();
                Car car = new Car();
                car.setCarId(-1);
                car.setMake("getCars():Em is not open error");
                car.setModel("getCars():We just have no entity manager to deal with");
                cars.add(car);
            }
            Query query = em.createNamedQuery("Car.findAll",Car.class);
            cars = query.getResultList();
            if (cars.size() == 1) {
                cars = new ArrayList<>();
                Car car = new Car();
                car.setCarId(-1);
                car.setMake("Query was non error car");
                car.setModel("We just have no cars to report :|");
                cars.add(car);
            }
        } catch (NoResultException e) {
            System.out.println("Exception when pulling car data via entity!");
            System.out.println("It is:" + e.getMessage());
            cars = new ArrayList<>();
            Car car = new Car();
            car.setCarId(-1);
            car.setMake("Exception Make:getCars():NoResultException");
            car.setModel(e.getMessage());
            cars.add(car);
        } catch (Exception e) {
            System.out.println("Exception when pulling car data via entity!");
            System.out.println("It is:" + e.getMessage());
            cars = new ArrayList<>();
            Car car = new Car();
            car.setCarId(-1);
            car.setMake("Exception Make:getCars():Generic");
            StringBuilder sb = new StringBuilder();
            sb.append("Message:[").append(e.getMessage()).append("]<br/>");
            if (e.getCause() != null) {
                Throwable cause = e.getCause();
                sb.append("Cause:[").append(cause.getMessage()).append("]<br/>");
            }
            for (StackTraceElement ste : e.getStackTrace()) {
                sb.append(ste.toString()).append("<br/>");
            }
            car.setModel(sb.toString());
            cars.add(car);
        }
        return cars;
    }

    public void createSampleCars() {
        try {
            Car car = new Car();
            car.setCarId(1);
            car.setMake("Test Car saved by app!");
            car.setModel("Tester, model... the car model");
            car.setYear(2021);
            em.persist(car);
        } catch (Exception e) {
            Car car = new Car();
            car.setCarId(1);
            car.setMake("Exception bus! createSampleCars()");
            car.setModel(e.getMessage());
            car.setYear(2021);
        }
    }

    public String doQuery(String query){
        return "";
    }
}
