/**
 * TLS-Scanner - A TLS Configuration Analysistool based on TLS-Attacker
 *
 * Copyright 2017-2019 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlsscanner.report.result.bleichenbacher;

import de.rub.nds.tlsattacker.attacks.config.BleichenbacherCommandConfig;
import de.rub.nds.tlsattacker.attacks.pkcs1.BleichenbacherWorkflowType;
import de.rub.nds.tlsattacker.attacks.pkcs1.VectorFingerprintPair;
import de.rub.nds.tlsattacker.attacks.util.response.EqualityError;
import java.util.List;

/**
 *
 * @author robert
 */
public class BleichenbacherTestResult {

    private final Boolean vulnerable;
    private final BleichenbacherCommandConfig.Type scanDetail;
    private final BleichenbacherWorkflowType workflowType;

    private final List<VectorFingerprintPair> vectorFingerPrintPairList;

    private final EqualityError equalityError;

    public BleichenbacherTestResult(Boolean vulnerable, BleichenbacherCommandConfig.Type scanDetail, BleichenbacherWorkflowType workflowType, List<VectorFingerprintPair> vectorFingerPrintPairList, EqualityError equalityError) {
        this.vulnerable = vulnerable;
        this.scanDetail = scanDetail;
        this.workflowType = workflowType;
        this.vectorFingerPrintPairList = vectorFingerPrintPairList;
        this.equalityError = equalityError;
    }

    public Boolean getVulnerable() {
        return vulnerable;
    }

    public BleichenbacherCommandConfig.Type getScanDetail() {
        return scanDetail;
    }

    public BleichenbacherWorkflowType getWorkflowType() {
        return workflowType;
    }

    public List<VectorFingerprintPair> getVectorFingerPrintPairList() {
        return vectorFingerPrintPairList;
    }

    public EqualityError getEqualityError() {
        return equalityError;
    }

}
