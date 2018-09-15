package ru.otus.l081.department;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class AtmDepartment {
    private List<Atm> atms = new ArrayList<>();

    void  addAtm (Atm atm) {
        atms.add(atm);
    }

    void removeAtm (Atm atm){
        atms.remove(atm);
    }

    public void upsetInitialSettings (){
        atms.forEach(Atm::resetInitialSetting);
    }

    public long getAllSums (){
        return atms.stream().
                flatMapToLong(atm -> LongStream.of(atm.getAvailableMoney())).
                sum();
    }
}
