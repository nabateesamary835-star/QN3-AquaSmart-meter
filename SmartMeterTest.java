public class SmartMeterTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        String rule = "==========================================";

        System.out.println(rule);
        System.out.println("  SmartMeter — Assertion-Based Tests");
        System.out.println(rule + "\n");

        testLoadTokenReopensClosedValve();
        testConsumptionBeyondCreditClosesValve();

        System.out.println(rule);
        System.out.println("  Results: " + passed + " passed, " + failed + " failed");
        System.out.println(rule);
    }

    static void testLoadTokenReopensClosedValve() {
        System.out.println("Test: loadToken re-opens a closed valve");
        SmartMeter meter = new SmartMeter("SM-T1", 0);

        assert meter.isValveOpen() : "Valve should be open at creation";

        meter.recordConsumption(1.0);
        assert !meter.isValveOpen() : "Valve should close after exhausting credit";

        meter.loadToken(500);
        assert meter.isValveOpen() : "Valve should reopen after token loaded";
        assert meter.getCreditBalance() == 500.0 : "Balance should be 500";

        passed++;
        System.out.println("  PASSED\n");
    }

    static void testConsumptionBeyondCreditClosesValve() {
        System.out.println("Test: consumption beyond credit closes the valve");
        SmartMeter meter = new SmartMeter("SM-T2", 100);

        boolean ok = meter.recordConsumption(3.0);

        assert !ok : "Should return false when credit insufficient";
        assert !meter.isValveOpen() : "Valve should close when credit runs out";
        assert meter.getCreditBalance() == 0.0 : "Balance should be 0";

        passed++;
        System.out.println("  PASSED\n");
    }
}
