class PlayTragedy extends Play{
    public PlayTragedy(String name) {
        super(name, "tragedy");
    }

    @Override
    public float playAmount(Performance perf){
        float thisAmount = 400; // Basic gain
        if (perf.audience > 30) { 
            thisAmount += 10 * (perf.audience - 30); //Aditional gain based on a condition
        }
        return thisAmount;
    }

    @Override
    public int playCredit(Performance perf){
        int volumeCredits = 0;
        volumeCredits += Math.max(perf.audience - 30, 0);
        return volumeCredits;
    }

}