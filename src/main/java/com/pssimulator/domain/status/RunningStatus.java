package com.pssimulator.domain.status;

import com.pssimulator.domain.pair.Pair;
import com.pssimulator.domain.pair.Pairs;
import com.pssimulator.domain.process.Processes;
import com.pssimulator.domain.processor.PowerConsumption;
import com.pssimulator.domain.processor.Processors;
import com.pssimulator.domain.queue.ReadyQueue;
import com.pssimulator.domain.time.IntegerTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RunningStatus {
    private final IntegerTime currentTime;
    private final Pairs pairs;
    private final PowerConsumption totalPowerConsumption;

    public static RunningStatus create() {
        return new RunningStatus(IntegerTime.createZero(), Pairs.createEmpty(), PowerConsumption.createZero());
    }

    public boolean isTerminatedProcessExist() {
        return pairs.isTerminatedProcessExist();
    }

    public boolean isProcessesEmpty() {
        return pairs.isEmpty();
    }

    public void addPair(Pair pair) {
        pairs.add(pair);
    }

    public Pairs getTerminatedPairs() {
        return pairs.getTerminatedPairs();
    }

    public Processes getTerminatedProcesses() {
        return pairs.getTerminatedProcesses();
    }

    public Processors getTerminatedProcessors() {
        return pairs.getTerminatedProcessors();
    }

    public Processors getProcessors() {
        return pairs.getProcessors();
    }

    public void removeTerminatedPairs() {
        pairs.removeTerminatedPairs();
    }

    public void removeTimeQuantumExpiredPairs(IntegerTime timeQuantum) {
        pairs.removeTimeQuantumExpiredPairs(timeQuantum);
    }

    public void updateWorkloadAndBurstTimeOfProcesses() {
        pairs.updateWorkloadAndBurstTimeOfProcesses();
    }

    public void updatePowerConsumption() {
        PowerConsumption increasedPowerConsumption = updatePowerConsumptionOfProcessors();
        updateTotalPowerConsumptionFrom(increasedPowerConsumption);
    }

    private PowerConsumption updatePowerConsumptionOfProcessors() {
        return pairs.updatePowerConsumptionOfProcessors();
    }

    private void updateTotalPowerConsumptionFrom(PowerConsumption increasedPowerConsumption) {
        totalPowerConsumption.increaseBy(increasedPowerConsumption);
    }

    public void increaseCurrentTime() {
        currentTime.increase();
    }

    public boolean isTimeQuantumExpiredProcessExist(IntegerTime timeQuantum) {
        return pairs.isTimeQuantumExpiredProcessExist(timeQuantum);
    }

    public Processes getTimeQuantumExpiredProcesses(IntegerTime timeQuantum) {
        return pairs.getTimeQuantumExpiredProcesses(timeQuantum);
    }

    public Processors getProcessorsAboutTimeQuantumExpiredProcesses(IntegerTime timeQuantum) {
        return pairs.getProcessorsAboutTimeQuantumExpiredProcesses(timeQuantum);
    }

    public boolean isLessRemainingWorkloadProcessExistIn(ReadyQueue readyQueue) {
        return pairs.isLessRemainingWorkloadProcessExistFrom(readyQueue);
    }

    public Pairs getBiggerRemainingWorkloadPairsComparedWith(ReadyQueue readyQueue) {
        return pairs.getBiggerRemainingWorkloadPairsComparedWith(readyQueue);
    }
}
