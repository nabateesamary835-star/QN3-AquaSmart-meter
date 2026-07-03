public class Main {
    public static void main(String[] args) {
        String bar = "--------------------------------------------------";

        System.out.println("=== Immy's AquaSmart Demo ===\n");

        SmartMeter meter = new SmartMeter("IMMY-MTR-007", 8000);

        System.out.println("Meter ID       : " + meter.getMeterId());
        System.out.println("Opening Credit : UGX " + meter.getCreditBalance());
        System.out.println("Valve Status   : " + (meter.isValveOpen() ? "OPEN" : "CLOSED"));
        System.out.println(bar);

        // ** Load token of UGX 3,000
        System.out.println("\n** Loading token of UGX 3,000 ...");
        meter.loadToken(3000);
        System.out.println("   Balance after load : UGX " + meter.getCreditBalance());

        // ** Consume 40 litres (cost = 2,000)
        System.out.println("\n** Dispensing 40 litres (cost UGX 2,000) ...");
        boolean ok = meter.recordConsumption(40);
        System.out.println("   Dispensed          : " + ok);
        System.out.println("   Remaining credit   : UGX " + meter.getCreditBalance());

        // ** Consume 200 litres (cost = 10,000, exceeds balance)
        System.out.println("\n** Dispensing 200 litres (cost UGX 10,000) ...");
        ok = meter.recordConsumption(200);
        System.out.println("   Dispensed          : " + ok);
        System.out.println("   Remaining credit   : UGX " + meter.getCreditBalance());
        System.out.println("   Valve status       : " + (meter.isValveOpen() ? "OPEN" : "CLOSED"));

        // ** Reload token of UGX 12,000
        System.out.println("\n** Reloading token of UGX 12,000 ...");
        meter.loadToken(12000);
        System.out.println("   Balance after load : UGX " + meter.getCreditBalance());
        System.out.println("   Valve status       : " + (meter.isValveOpen() ? "OPEN" : "CLOSED"));

        // ** Consume 80 litres (cost = 4,000)
        System.out.println("\n** Dispensing 80 litres (cost UGX 4,000) ...");
        ok = meter.recordConsumption(80);
        System.out.println("   Dispensed          : " + ok);
        System.out.println("   Final credit       : UGX " + meter.getCreditBalance());

        System.out.println("\n" + bar);
        System.out.println("  Demo complete.");
        System.out.println(bar);
    }
}
