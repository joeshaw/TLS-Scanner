/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rub.nds.tlsscanner.report.after;

import de.rub.nds.tlsattacker.core.constants.CompressionMethod;
import de.rub.nds.tlsscanner.probe.handshakeSimulation.SimulatedClient;
import de.rub.nds.tlsscanner.report.SiteReport;

public class HandshakeSimulationAfterProbe extends AfterProbe {

    @Override
    public void analyze(SiteReport report) {
        int secureConnections = 0;
        for (SimulatedClient simulatedClient : report.getSimulatedClientList()) {
            if (simulatedClient.isReceivedServerHelloDone()) {
                if (report.getPaddingOracleVulnerable() && simulatedClient.getSelectedCiphersuite().isCBC()) {
                    simulatedClient.setPaddingOracleVulnerable(true);
                    simulatedClient.setConnectionSecure(false);
                }
                if (report.getBleichenbacherVulnerable() && simulatedClient.getSelectedCiphersuite().name().contains("TLS_RSA")) {
                    simulatedClient.setBleichenbacherVulnerable(true);
                    simulatedClient.setConnectionSecure(false);
                }
                if (simulatedClient.getSelectedCompressionMethod() != CompressionMethod.NULL) {
                    simulatedClient.setCrimeVulnerable(true);
                    simulatedClient.setConnectionSecure(false);
                }
                if (report.getInvalidCurveVulnerable() && simulatedClient.getSelectedCiphersuite().name().contains("TLS_ECDH")) {
                    simulatedClient.setInvalidCurveVulnerable(true);
                    simulatedClient.setConnectionSecure(false);
                }
                if (report.getInvalidCurveEphermaralVulnerable() && simulatedClient.getSelectedCiphersuite().name().contains("TLS_ECDHE")) {
                    simulatedClient.setInvalidCurveEphemeralVulnerable(true);
                    simulatedClient.setConnectionSecure(false);
                }
                if (report.getSweet32Vulnerable()) {
                    if (simulatedClient.getSelectedCiphersuite().name().contains("3DES") || 
                            simulatedClient.getSelectedCiphersuite().name().contains("IDEA") || 
                            simulatedClient.getSelectedCiphersuite().name().contains("GOST")) {
                        simulatedClient.setSweet32Vulnerable(true);
                        simulatedClient.setConnectionSecure(false);
                    }
                }
                if (simulatedClient.isConnectionSecure()==true) {
                    secureConnections++;
                }
            }
        }
        report.setConnectionSecureCounter(secureConnections);
        report.setConnectionInsecureCounter(report.getHandshakeSuccessfulCounter()-secureConnections);
    }
    
}