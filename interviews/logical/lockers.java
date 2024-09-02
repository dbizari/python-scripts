// An Amazon pickup location has various lockers for packages to be dropped
//  off and picked up. We have both packages and lockers of varying sizes. 
//  Model the lockers, packages, and pickup location and implement an algorithm 
//  to find the best possible empty locker for a given package efficiently.


// PickUpLocation -> List<Locker> lockers 
//                -> 

// Lockers -> Pick up / drop off operation
//         -> boolean isBusy
//         -> Size size (small, medium, large)

// Packages -> Dimension

public enum Size {
    SMALL(0),
    MEDIUM(1),
    LARGE(2);

    public static Size nextSize(Size size) {
        switch (size) {
            case SMALL:
                return MEDIUM;
            case MEDIUM:
                return LARGE;
            default:
                return null; // No next size for LARGE
        }
    }
}

public class PickUpLocation { 
    private Map<Size, List<Locker>> lockersBySize; // We can use also a Queue to improve performance
    private Map<String, Locker> busyLockers;
    private String name;
    // ... PickUpLocation metadata

    public PickUpLocation(List<Locker> lockers) {
        this.lockersBySize = new HashMap<Size, List<Locker>>();
        this.busyLockers = new HashMap<Stirng, Locker>();

        for (Size s:Size.values()) {
            this.lockersBySize.put(new ArrayList<Locker>());
        }
        
        for(Locker l: lockers) {
            Size lockerSize = l.getSize();
            this.lockersBySize.get(lockerSize).add(locker);
        }
    }

    public String dropOffPackage(Package package) {
        Size packageSize = package.getSize();
        while (packageSize != null) {
            List<Locker> lockers = this.lockersBySize.get(packageSize);
            for (Locker l: lockers) {
                if (l.isAvailable()) {
                    l.assignPackage(p);
                    String lockerID = l.getID()
                    this.busyLockers.put(lockerID)
                    return lockerID;
                }
            }
            packageSize = Size.nextSize(packageSize);
        }
        
        throw new LockerNotAvailableException();
    }

    public void pickUpPackage(String lockerID) {
        if (!this.busyLockers.containsKey(lockerID)) {
            return UnusedLockerException();
        }

        Locker l = this.busyLockers.get(lockerID);
        this.busyLockers.delete(lockerID);

        return l.pickUpPackage();
    }
}

public class Locker {
    private Size size;
    private String lockerID;
    private boolean isAvailable;
    private Package payload;
    // Locker metadata...

    public Locker(Size size, String lockerID) {
        this.size = size;
        this.lockerID = lockerID;
        this.isAvailable = true;
        this.payload = null;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void assignPackage(Package package) {
        this.payload = package;
        this.isAvailable = false;
    }

    public Package pickUpPackage() {
        this.isAvailable = true;
        Package p = this.payload;
        this.payload = null
        return p;
    }
}

public class Package {
    private Size size;
    private String packageID;
    // Package metadata

    public Package(Size size, String packageID) {
        this.size = size;
        this.packageID = packageID;
    }

    public Size getSize() {
        return this.size;
    }

    // Getters for other attributes
}