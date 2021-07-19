/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author multicoder
 */
@Entity
@Table(name = "cars")
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT C from Car C"),
    @NamedQuery(name = "Car.findAll", query = "SELECT C from Car C WHERE C.make=:make")
})
public class Car implements Serializable {

    @Id
    @Column(name = "CarId")

    private int carId;

    @Size(max = 500)
    @Column(name = "make")
    private String make;

    @Size(max = 9999)
    @Column(name = "model")
    private String model;

    @Min(value = 1)
    @Max(value = 9999)
    @Column(name = "year")
    private int year;

    public String getMake() {
        return make;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
