package api.payload;

public class Store {

    int id;
    long petId;
    int quantity;
    String shipDate="2023-09-09T11:57:24.436+0000";
    String status;
    boolean complete;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }



}
