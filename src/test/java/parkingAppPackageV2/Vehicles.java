package parkingAppPackageV2;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Vehicles implements Serializable {
	
	protected String plate;
	protected int slot;
	protected String carType;
	protected int chargeRate;
	
	Vehicles(String plate,int slot,String carType,int chargeRate){
		this.plate=plate;
		this.slot=slot;
		this.carType=carType;
	}
	Vehicles(){}
	

	public String getPlate() {
		return this.plate;
	}
	public int getSlot() {
		return this.slot;
	}
	public String getCarType() {
		return this.carType;
	}
	public int getChargeRate() {
		return this.chargeRate;
	}
	public void setPlate(String plate) {
		this.plate=plate;
	}
	public void setSlot(int slot) {
		this.slot=slot;
	}
	public void setChargeRate(int chargeRate) {
		this.chargeRate=chargeRate;
	}
	public void setCarType(String carType) {
		this.carType=carType;
	}
}

