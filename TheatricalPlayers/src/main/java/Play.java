abstract class Play {
	public String name;
 
  	public Play(String name) {
   		this.name = name;
	}
  

/********************************************************************/
/*playAmount: generate the amount for a given performance			*/
/*                                                                	*/
/*Input: Performance perf					                      	*/
/*                                                                	*/
/*Output: Float the amount					                		*/
/********************************************************************/
	abstract float playAmount(Performance perf);


/********************************************************************/
/*playCredit: generate the credit for a given performance			*/
/*                                                                	*/
/*Input: Performance perf					                      	*/
/*                                                                	*/
/*Output: Int the credit					                		*/
/********************************************************************/
	abstract int playCredit(Performance perf);

}
