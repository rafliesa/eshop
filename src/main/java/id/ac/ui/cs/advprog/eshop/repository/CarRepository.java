package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

@Repository
public class CarRepository implements BaseModelrepository<Car>{
    static int id = 0;
    private List<Car> carData = new ArrayList<>();

    @Override
    public Car create(Car car) {
        if (car.getId() == null) {
            UUID uuid = UUID.randomUUID();
            car.setId(uuid.toString());
        }
        carData.add(car);
        return car;
    }

    @Override
    public Iterator<Car> findAll(){
        return carData.iterator();
    }

    @Override
    public Car findById(String id) {
        for (Car car : carData) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    @Override
    public Car edit(String id, Car updateCar) {
        for (int i = 0; i < carData.size(); i++) {
            Car car = carData.get(i);
            if (car.getId().equals(id)) {
                car.setName(updateCar.getName());
                car.setCarColor(updateCar.getCarColor());
                car.setQuantity(updateCar.getQuantity());
                return car;
            }
        }
        return null;
    }

    @Override
    public boolean delete(String id) {return carData.removeIf(car -> car.getId().equals(id));}
}
