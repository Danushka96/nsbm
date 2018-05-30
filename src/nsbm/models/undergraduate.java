package nsbm.models;

public class undergraduate extends student {
    private int result_id,rank;
    private String stream;
    undergraduate(String id, String name,String nic,String email, String DOB, String address, String tp, int result_id, int rank, String stream){
        super(id,name,nic,email,DOB,address,tp);
        this.result_id=result_id;
        this.rank=rank;
        this.stream=stream;
    }
    undergraduate(){
        super();
        this.result_id=0;
        this.rank=0;
        this.stream=null;
    }
//    Setters
    public void setResult_id(int result_id){ this.result_id=result_id; }
    public void setRank(int rank){ this.rank=rank; }
    public void setStream(String stream){ this.stream=stream; }
// Getters
    public int getRank(){
        return this.rank;
    }
    public int getResult_id(){
        return this.result_id;
    }
    public String getStream(){
        return this.stream;
    }
}
