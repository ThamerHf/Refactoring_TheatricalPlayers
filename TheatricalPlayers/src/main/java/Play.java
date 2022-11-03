abstract class Play {
	public String name;
	public String type;
 
  	public Play(String name, String type) {
   	this.name = name;
    	this.type = type;
	}
  abstract float playAmount(Performance perf);
  abstract int playCredit(Performance perf);
}
