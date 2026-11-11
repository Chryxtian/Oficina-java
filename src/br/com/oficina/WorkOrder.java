package br.com.oficina;

import java.math.BigDecimal;

public class WorkOrder {

        private Vehicle vehicle;
        private String complaint;
        private String status;
        private int number;
        private String diagnosis;
        private String service;
        private String partsNeeded;
        private BigDecimal price;
        private boolean customerNotified;



        public WorkOrder(int number, Vehicle vehicle, String complaint) {
            this.vehicle = vehicle;
            this.complaint = complaint;
            this.status = "Aberta";
            this.number = number;


        }

        public Vehicle getVehicle(){

            return vehicle;
        }


        public String getComplaint(){
            return complaint;

        }

        public String getStatus() {

            return status;
        }

        public int getNumber(){
            return number;
        }

        public void setStatus(String status){

            this.status = status;
        }

        public String getDiagnosis() {
            return diagnosis;
        }

        public void setDiagnosis(String diagnosis){
            this.diagnosis = diagnosis;
        }

        public String getService(){
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getPartsNeeded(){
            return partsNeeded;
        }

        public void setPartsNeeded(String partsNeeded) {
            this.partsNeeded = partsNeeded;
        }

        public BigDecimal getPrice(){
            return price;
        }

        public void setPrice(BigDecimal price){
            this.price = price;
        }

        public boolean isCustomerNotified(){
            return customerNotified;
        }

        public void setCustomerNotified(boolean customerNotified){
            this.customerNotified = customerNotified;
        }
}
