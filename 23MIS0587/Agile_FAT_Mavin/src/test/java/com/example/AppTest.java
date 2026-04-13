package com.example;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class AppTest {
    @Test
    public void testEligibleVoter() {
        assertEquals("Eligible", App.getEligibilityStatus(25, true, true));
    }
    @Test
    public void testUnderageVoter() {
        assertEquals("Ineligible (Reason: Underage)", App.getEligibilityStatus(17, true, true));
    }
    @Test
    public void testNonCitizen() {
        assertEquals("Ineligible (Reason: Not a citizen)", App.getEligibilityStatus(30, false, true));
    }
    @Test
    public void testInvalidID() {
        assertEquals("Ineligible (Reason: Invalid ID)", App.getEligibilityStatus(20, true, false));
    }
}
