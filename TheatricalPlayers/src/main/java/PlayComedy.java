class PlayComedy extends Play{
    private String type;

    public PlayComedy(String name) {
        super(name);
        this.type = "comedy";
    }

    public float playAmount(Performance perf){
        float thisAmount = 300; //basic gain
          if (perf.audience > 20) {
            thisAmount += 100 + 5 * (perf.audience - 20); //Aditional gain based on a condition
          }

          thisAmount += 3 * perf.audience; 
          return thisAmount;
    }

    public int playCredit(Performance perf){
        int volumeCredits = 0;
        volumeCredits += Math.max(perf.audience - 30, 0);
        volumeCredits += Math.floor(perf.audience / 5);
        return volumeCredits;
    }

    public String getType(){
		return this.type;
	}
}