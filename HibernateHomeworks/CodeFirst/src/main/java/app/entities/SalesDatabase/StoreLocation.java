//package app.entities.SalesDatabase;
//
//
//import app.entities.BaseEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import java.util.Set;
//
//@Entity
//@Table(name = "store_locations")
//public class StoreLocation extends BaseEntity {
//
//    private String locationName;
//    private Set<Sale> saleSet;
//
//    public StoreLocation() {
//    }
//
//    @Column(name = "location_name")
//    public String getLocationName() {
//        return locationName;
//    }
//
//    public void setLocationName(String locationName) {
//        this.locationName = locationName;
//    }
//
//    @OneToMany(targetEntity = Sale.class,mappedBy = "storeLocation")
//    public Set<Sale> getSaleSet() {
//        return saleSet;
//    }
//
//    public void setSaleSet(Set<Sale> saleSet) {
//        this.saleSet = saleSet;
//    }
//}
