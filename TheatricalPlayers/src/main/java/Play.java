abstract class Play {
	public String name;
 
  	public Play(String name) {
   		this.name = name;
	}
  
	abstract float playAmount(Performance perf);

   	abstract int playCredit(Performance perf);

}
