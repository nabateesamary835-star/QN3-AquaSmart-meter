public class SmartMeter {
    private String meterId;
    private double creditBalance;
    private boolean valveOpen;

    public SmartMeter(String meterId, double creditBalance) {
        this.meterId = meterId;
        this.creditBalance = creditBalance;
        this.valveOpen = true;
    }

    public double loadToken(double amount) {
        if (amount > 0) {
            creditBalance += amount;
            valveOpen = true;
        }
        return creditBalance;
    }

    public boolean recordConsumption(double litres) {
        final double COST_PER_LITRE = 50.0;
        double cost = litres * COST_PER_LITRE;

        if (!valveOpen) {
            return false;
        }

        if (cost > creditBalance) {
            creditBalance = 0;
            valveOpen = false;
            return false;
        }

        creditBalance -= cost;
        if (creditBalance <= 0) {
            creditBalance = 0;
            valveOpen = false;
        }
        return true;
    }

    public String getMeterId() {
        return meterId;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public boolean isValveOpen() {
        return valveOpen;
    }
}
