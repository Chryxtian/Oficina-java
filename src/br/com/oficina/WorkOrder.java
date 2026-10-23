package br.com.oficina;

public class WorkOrder {

        private Vehicle vehicle;
        private String complaint;
        private String status;



        public WorkOrder(Vehicle vehicle, String complaint) {
            this.vehicle = vehicle;
            this.complaint = complaint;
            this.status = "Aberta";


        }

        public Vehicle getVehicle(){
            return vehicle;
        }


        public String getComplaint(){
            return complaint;

        }

        public String getStatus(){
            return status;
        }

        public void setStatus(String status){
            this.status = status;
        }
}
