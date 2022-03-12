package ru.mephi;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.correlation.Covariance;

import java.io.IOException;
import java.util.LinkedHashMap;

public class Calculator {

    private double[] cacheX;
    private double[] cacheY;
    private double[] cacheZ;
    private LinkedHashMap<String, Double[]> hashMap;

    public LinkedHashMap<String, Double[]> getHashMap(DataImporter dataImporter) throws IOException {
        if (hashMap == null) {
            fillHashMap(dataImporter);
        }
        return hashMap;
    }

    public void cacheVariables(DataImporter dataImporter) throws IOException {
        cacheX = dataImporter.readData(0);
        cacheY = dataImporter.readData(1);
        cacheZ = dataImporter.readData(2);
    }

    public void fillHashMap(DataImporter dataImporter) throws IOException {
        if (hashMap == null) {
            hashMap = new LinkedHashMap<>();
        }
        if (cacheX  == null || cacheY == null || cacheZ == null) {
            cacheVariables(dataImporter);
        }
        Double[] temp = {StatUtils.geometricMean(cacheX),
                StatUtils.geometricMean(cacheY),
                StatUtils.geometricMean(cacheZ)};
        hashMap.put("среднее геометрическое", temp);

        temp = new Double[]{StatUtils.mean(cacheX),
                StatUtils.mean(cacheY),
                StatUtils.mean(cacheZ)};

        hashMap.put("среднее арифметическое", temp);

        temp = new Double[] {Math.sqrt(StatUtils.variance(cacheX)),
                Math.sqrt(StatUtils.variance(cacheY)),
                Math.sqrt(StatUtils.variance(cacheZ))};

        hashMap.put("оценка стандартного отклонения", temp);

        temp = new Double[]{StatUtils.max(cacheX) - StatUtils.min(cacheX),
                StatUtils.max(cacheY) - StatUtils.min(cacheY),
                StatUtils.max(cacheZ) - StatUtils.min(cacheZ)};

        hashMap.put("размах", temp);

        temp = new Double[]{new Covariance().covariance(cacheX,cacheY),
                new Covariance().covariance(cacheX,cacheZ),
                new Covariance().covariance(cacheZ,cacheY)};

        hashMap.put("коэффициенты ковариации", temp);

        temp = new Double[]{(double) cacheX.length,
                (double) cacheY.length,
                (double) cacheZ.length};

        hashMap.put("количество элементов", temp);

        temp = new Double[]{Math.sqrt(StatUtils.variance(cacheX)) / Math.abs(StatUtils.mean(cacheX)),
                Math.sqrt(StatUtils.variance(cacheY)) / Math.abs(StatUtils.mean(cacheY)),
                Math.sqrt(StatUtils.variance(cacheZ)) / Math.abs(StatUtils.mean(cacheZ))};

        hashMap.put("коэффициент вариации", temp);

        temp = new Double[]{StatUtils.mean(cacheX) - (new TDistribution(cacheX.length - 1).inverseCumulativeProbability(0.95) * Math.sqrt(StatUtils.variance(cacheX))) / Math.sqrt(cacheX.length),
                StatUtils.mean(cacheY) - (new TDistribution(cacheY.length - 1).inverseCumulativeProbability(0.95) * Math.sqrt(StatUtils.variance(cacheY))) / Math.sqrt(cacheY.length),
                StatUtils.mean(cacheZ) - (new TDistribution(cacheZ.length - 1).inverseCumulativeProbability(0.95) * Math.sqrt(StatUtils.variance(cacheZ))) / Math.sqrt(cacheZ.length)};

        hashMap.put("-доверительный интервал", temp);

        temp = new Double[]{StatUtils.mean(cacheX) + (new TDistribution(cacheX.length - 1).inverseCumulativeProbability(0.95) * Math.sqrt(StatUtils.variance(cacheX))) / Math.sqrt(cacheX.length),
                StatUtils.mean(cacheY) + (new TDistribution(cacheY.length - 1).inverseCumulativeProbability(0.95) * Math.sqrt(StatUtils.variance(cacheY))) / Math.sqrt(cacheY.length),
                StatUtils.mean(cacheZ) + (new TDistribution(cacheZ.length - 1).inverseCumulativeProbability(0.95) * Math.sqrt(StatUtils.variance(cacheZ))) / Math.sqrt(cacheZ.length)};

        hashMap.put("+доверительный интервал", temp);

        temp = new Double[]{StatUtils.variance(cacheX),
                StatUtils.variance(cacheY),
                StatUtils.variance(cacheZ)};

        hashMap.put("оценка дисперсии", temp);

        temp = new Double[]{StatUtils.max(cacheX),
                StatUtils.max(cacheY),
                StatUtils.max(cacheZ)};

        hashMap.put("максимумы", temp);

        temp = new Double[]{StatUtils.min(cacheX),
                StatUtils.min(cacheY),
                StatUtils.min(cacheZ)};

        hashMap.put("минимумы", temp);

    }

//    public static void main(String[] args) throws IOException {
//        DataImporter dataImporter = new DataImporter();
//        Calculator calculator = new Calculator();
//        for (Map.Entry<String, Double[]> stringEntry : calculator.getHashMap(dataImporter).entrySet()) {
//            System.out.println(stringEntry.getKey() + ": ");
//            System.out.println(stringEntry.getValue()[0] + " x");
//            System.out.println(stringEntry.getValue()[1] + " y");
//            System.out.println(stringEntry.getValue()[2] + " z");
//        }
////        Application.launch();
////
//    }
}
