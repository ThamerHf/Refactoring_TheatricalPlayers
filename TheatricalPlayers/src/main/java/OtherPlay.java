import java.util.*;

class OtherPlay extends Play{
    private String type;
    
    public OtherPlay(String name, String type) {
        super(name);
        this.setType(type);
    }

  public float playAmount(Performance perf){
      return -1;
  }

     public int playCredit(Performance perf){
      return -1;
  }

  public void setType(String type){
      if(type == "comedy"){
          throw new Error("Impossible to change to type comedy");
      }

      if(type == "tragedy"){
          throw new Error("Impossible to change to type tragedy");
      }

      this.type = type;
  }

    public String getType(){
		return this.type;
	}
}