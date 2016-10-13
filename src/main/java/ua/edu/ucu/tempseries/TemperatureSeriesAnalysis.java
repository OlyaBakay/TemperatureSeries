package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private int numberTemperature;
    private int p;
    private double avarageNum;
    private double sum;
    private double min1;
    private double max1;
    private double closestToZero;
    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() { }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
        for (double i: temperatureSeries) {
            if (i < -273) {
                throw new InputMismatchException();
            } }
    }

    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            for (double i : temperatureSeries) {
                avarageNum += i;
            }
            avarageNum = avarageNum / temperatureSeries.length;
            return avarageNum;
        }
    }

    public double deviation() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            for (double i : temperatureSeries) {
                sum += (i - average())*(i - average());
            }
            double quadraticDeviation = //
                    Math.sqrt(sum / temperatureSeries.length);
            return Math.round(quadraticDeviation);
        }
    }

    public double min() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            double min1 = temperatureSeries[0];
            for (int i = 1; i < temperatureSeries.length; i++) {
                if (temperatureSeries[i] < min1) {
                    min1 = temperatureSeries[i];
                }
            }
            return min1;
        }
    }

    public double max() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            double max1 = temperatureSeries[0];
            for (int i = 1; i < temperatureSeries.length; i++) {
                if (temperatureSeries[i] > max1) {
                    max1 = temperatureSeries[i];
                }
            }
            return max1;
        }
    }

    public double findTempClosestToZero() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            closestToZero = temperatureSeries[0];
            for (int i = 1; i < temperatureSeries.length; i++) {
                if (Math.abs(temperatureSeries[i]) < Math.abs(closestToZero)) {
                    closestToZero = temperatureSeries[i];
                } else if (Math.abs(temperatureSeries[i]) == //
                        Math.abs(closestToZero) && temperatureSeries[i] > 0) {
                    closestToZero = temperatureSeries[i];
                }
            }
            return closestToZero;
        }
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        if (temperatureSeries.length == 1) {
            return temperatureSeries[0];
        }
        else {
            double idx = 0;
            double distance1 = Math.abs(temperatureSeries[0] - tempValue);
            for (int i = 1; i < temperatureSeries.length; i++) {
                double distance2 = Math.abs(temperatureSeries[i] - tempValue);
                if (distance1 > distance2) {
                    idx = temperatureSeries[i];
                    distance1 = distance2;
                }
            }
            return idx;
        } }

    public double[] findTempsLessThen(double tempValue) {
        double[] newList1 = new double[temperatureSeries.length];
        for (int i = 0; i < temperatureSeries.length //
                && temperatureSeries[i] < tempValue; i++) {
            p++;
            newList1[i] = temperatureSeries[i];
        }
        double[] newList2 = new double[p];
        for (int i = 0; i < newList2.length; i++) {
            newList2[i] = newList1[i];
        }
        return newList2;

    }

    public double[] findTempsGreaterThen(double tempValue) {
        int k = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if  (temperatureSeries[i] >= tempValue) {
                k++; }
        }
        double[] newList1 = new double[k];
        int c = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] >= tempValue) {
                newList1[c] = temperatureSeries[i];
                c++;
            }

        }
        return newList1; }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            final TempSummaryStatistics summaryStatistics = //
                    new TempSummaryStatistics(this.average(), this.deviation(),
                    this.min(), this.max());
            return summaryStatistics;
        }
    }

    public int addTemps(double... temps) {
        double[] newTempSeries = new double[Math.max( //
                temperatureSeries.length+temps.length, temperatureSeries.length * 2)];
        for (int i = 0; i < temps.length; i++) {
            newTempSeries[i] = temps[i];
        }
        for (int i = 0; i < this.temperatureSeries.length; i++) {
            newTempSeries[i+temps.length] = this.temperatureSeries[i];
        }
        numberTemperature = temperatureSeries.length + temps.length;
        return numberTemperature;
    } }