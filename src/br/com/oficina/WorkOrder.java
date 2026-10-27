package br.com.oficina;

public class WorkOrder {

        private Vehicle vehicle;
        private String complaint;
        private String status;
        private int number;



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
}
