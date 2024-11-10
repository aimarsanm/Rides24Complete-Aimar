package businesslogic;

import javax.swing.table.AbstractTableModel;

import domain.Driver;
import domain.Ride;

public class DriverAdapter extends AbstractTableModel {
    
	private static final long serialVersionUID = 1L;
	private Driver driver;

    public DriverAdapter(Driver driver) {
        this.driver = driver;
    }

    @Override
    public int getRowCount() {
        return driver.getCreatedRides().size();  // Número de viajes
    }

    @Override
    public int getColumnCount() {
        return 5;  // Por ejemplo, nombre del viaje y fecha
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ride ride = driver.getCreatedRides().get(rowIndex);
        if (columnIndex == 0) {
            return ride.getFrom();
        } else if(columnIndex == 1) {
            return ride.getTo();
        }else if(columnIndex == 2) {
            return ride.getDate();
        }else if(columnIndex == 3) {
            return ride.getnPlaces();
        }else{
            return ride.getPrice();
        }
    }
    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "From";
        } else if (columnIndex == 1) {
            return "To";
        } else if (columnIndex == 2) {
            return "Date";
        } else if (columnIndex == 3) {
            return "Places";
        } else {
            return "Price";
        }
    }
}
