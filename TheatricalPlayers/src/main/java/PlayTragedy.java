class PlayTragedy extends Play{
    private String type;

    public PlayTragedy(String name) {
        super(name);
        this.type = "tragedy";
    }

    public float playAmount(Performance perf){
        float thisAmount = 400; // Basic gain
        if (perf.audience > 30) { 
            thisAmount += 10 * (perf.audience - 30); //Aditional gain based on a condition
        }
        return thisAmount;
    }

    public int playCredit(Performance perf){
        int volumeCredits = 0;
        volumeCredits += Math.max(perf.audience - 30, 0);
        return volumeCredits;
    }

    public String getType(){
		return this.type;
	}
}