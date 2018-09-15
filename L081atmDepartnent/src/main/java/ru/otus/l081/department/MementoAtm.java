package ru.otus.l081.department;

public class MementoAtm {

        private Atm atm;
        public MementoAtm(Atm atm) {
            this.atm = new Atm(atm);
        }

        public Atm getSavedAtm() {
            return atm;
        }

}
